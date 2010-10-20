package gsn.wrappers;

import gsn.beans.DataField;
import gsn.beans.DataTypes;
import gsn.beans.StreamElement;

import java.io.Serializable;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MonitorInfo;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.apache.log4j.Logger;

public class GSNStatsWrapper extends AbstractWrapper
{
	private final transient Logger logger = Logger.getLogger( GSNStatsWrapper.class );
	
	private final static int DEFAULT_SAMPLING_RATE_MS = 30000;
	
	private final static DataField[] outputStructure = new DataField[] {
															new DataField("uptime", DataTypes.INTEGER),
															new DataField("memory_heap_used", DataTypes.INTEGER),
															new DataField("memory_nonheap_used", DataTypes.INTEGER),
															new DataField("thread_blocked_cnt", DataTypes.SMALLINT),
															new DataField("thread_new_cnt", DataTypes.SMALLINT),
															new DataField("thread_runnable_cnt", DataTypes.SMALLINT),
															new DataField("thread_terminated_cnt", DataTypes.SMALLINT),
															new DataField("thread_timed_waiting_cnt", DataTypes.SMALLINT),
															new DataField("thread_waiting_cnt", DataTypes.SMALLINT),
															new DataField("thread_blocked_rate", DataTypes.INTEGER),
															new DataField("thread_waited_rate", DataTypes.INTEGER),
															};
	
	private int sampling_rate = DEFAULT_SAMPLING_RATE_MS;
	private boolean stopped = false;
	private Object event = new Object();
	
	public boolean initialize()	{
		String predicate = getActiveAddressBean().getPredicateValue("sampling-rate");
		if ( predicate != null ) {
			try {
				sampling_rate = Integer.parseInt(predicate);
			} catch (NumberFormatException e) {
				logger.warn("sampling-rate is not parsable, set to default ("+DEFAULT_SAMPLING_RATE_MS+"ms)");
			}
		}

		return true;
	}
	
	public void run() {
		short thread_blocked_cnt, thread_new_cnt, thread_runnable_cnt, thread_terminated_cnt, thread_timed_waiting_cnt, thread_waiting_cnt;
		long timestamp, thread_blocked_acc, thread_waited_acc, diff, blockedCount, blockedTime, waitedCount, waitedTime;
		long old_timestamp = -1, thread_blocked_acc_old = -1, thread_waited_acc_old = -1;
		ThreadInfo[] threads;
		MonitorInfo[] monitors;
		LockInfo[] locks;
		Serializable[] output = new Serializable[outputStructure.length];
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		while (!stopped) {
			try {
				synchronized (event) {
					event.wait(sampling_rate);
				}
			} catch (InterruptedException e) {
				break;
			}
		
			thread_blocked_cnt = 0;
			thread_new_cnt = 0;
			thread_runnable_cnt = 0;
			thread_terminated_cnt = 0;
			thread_timed_waiting_cnt = 0;
			thread_waiting_cnt = 0;
			thread_blocked_acc = 0;
			thread_waited_acc = 0;
			
			timestamp = System.currentTimeMillis();
			
			output[0] = (int) (runtimeBean.getUptime() / 1000);
			output[1] = (int) (memoryBean.getHeapMemoryUsage().getUsed() / 1024);
			output[2] = (int) (memoryBean.getNonHeapMemoryUsage().getUsed() / 1024);
			
			threads = threadBean.getThreadInfo(threadBean.getAllThreadIds());
			for (int i=0; i<threads.length; i++) {
				switch (threads[i].getThreadState()) {
				case BLOCKED:
					thread_blocked_cnt++;
					break;
				case NEW:
					thread_new_cnt++;
					break;					
				case RUNNABLE:
					thread_runnable_cnt++;
					break;
				case TERMINATED:
					thread_terminated_cnt++;
					break;
				case TIMED_WAITING:
					thread_timed_waiting_cnt++;
					break;
				case WAITING:
					thread_waiting_cnt++;
					break;
				}
				blockedCount = threads[i].getBlockedCount();
				blockedTime = threads[i].getBlockedTime();
				waitedCount = threads[i].getWaitedCount();
				waitedTime = threads[i].getWaitedTime();
				if (logger.isInfoEnabled()) {
					logger.info(threads[i].getThreadName()+" blocked:"+blockedCount+":"+blockedTime+"ms waited:"+waitedCount+":"+waitedTime+"ms");
				}
				if (logger.isDebugEnabled()) {
					monitors = threads[i].getLockedMonitors();
					locks = threads[i].getLockedSynchronizers();
					for (int m=0; m<monitors.length; m++) {
						logger.debug(threads[i].getThreadName()+" monitor:"+monitors[m].getClassName());
					}
					for (int l=0; l<locks.length; l++) {
						logger.debug(threads[i].getThreadName()+" lock:"+locks[l].getClassName());
					}
				}
				thread_blocked_acc += threads[i].getBlockedCount();
				thread_waited_acc += threads[i].getWaitedCount();
			}
			
			output[3] = thread_blocked_cnt;
			output[4] = thread_new_cnt;
			output[5] = thread_runnable_cnt;
			output[6] = thread_terminated_cnt;
			output[7] = thread_timed_waiting_cnt;
			output[8] = thread_waiting_cnt;
			if (old_timestamp != -1) {
				diff = timestamp - old_timestamp;
				output[9] = (int) ((thread_blocked_acc - thread_blocked_acc_old) * 1000 / diff);
				output[10] = (int) ((thread_waited_acc - thread_waited_acc_old) * 1000 / diff);
			} else {
				output[9] = null;
				output[10] = null;
			}
			
			postStreamElement(new StreamElement(outputStructure, output, timestamp));
			
			old_timestamp = timestamp;
			thread_blocked_acc_old = thread_blocked_acc;
			thread_waited_acc_old = thread_waited_acc;
		}
	}

	public void dispose() {
		stopped = true;
		synchronized (event) {
			event.notify();
		}
	}

	public DataField[] getOutputFormat() {
		return outputStructure;
	}

	public String getWrapperName() {
		return "GSNStatsWrapper";
	}
}
