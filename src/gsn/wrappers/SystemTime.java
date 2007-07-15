package gsn.wrappers;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.beans.StreamElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.Timer;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.buffer.SynchronizedBuffer;
import org.apache.commons.collections.buffer.UnboundedFifoBuffer;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

/**
 * This wrapper presents the system current clock. 
 */

public class SystemTime extends AbstractWrapper implements ActionListener {
  
  public static final String CLOCK_PERIOD_KEY = "clock-period";

  public static final String MAX_DELAY_KEY = "max-delay";
  
  private static final Serializable [ ] EMPTY_DATA_PART   = new Serializable [ ] {};
  
  private static final Byte [ ]      EMPTY_FIELD_TYPES = new Byte [ ] {};
  
  private static final int              DEFAULT_CLOCK_PERIODS     = 1 * 1000;
  
  private static final int DEFAULT_MAX_DELAY = 10 * 1000;
  
  private String [ ]                    EMPTY_FIELD_LIST  = new String [ ] {};
  
  private  DataField   []    collection        = new DataField[] {};
  
  private static int                    threadCounter     = 0;
  
  private  transient Logger        logger            = Logger.getLogger( this.getClass() );
  
  private Timer                         timer;

  private boolean                      delayPostingElements = false;
  
  private int maximumDelay = DEFAULT_MAX_DELAY;
  
  private Buffer streamElementBuffer; 
  
  private Object objectLock = new Object();
  
  public boolean initialize (  ) {
    setName( "LocalTimeWrapper-Thread" + ( ++threadCounter ) );
    AddressBean addressBean =getActiveAddressBean ( );
    //  TODO: negative values?
    timer = new Timer(  addressBean.getPredicateValueAsInt( CLOCK_PERIOD_KEY ,DEFAULT_CLOCK_PERIODS) , this );
    maximumDelay = addressBean.getPredicateValueAsInt(MAX_DELAY_KEY, DEFAULT_MAX_DELAY);
    if(maximumDelay > 0){
    	streamElementBuffer = SynchronizedBuffer.decorate(new UnboundedFifoBuffer());
    	delayPostingElements = true;
    }
    return true;
  }
  
  public void run ( ) {
    timer.start( );
    if(delayPostingElements){
    	while(isActive()){
    		synchronized(objectLock){
    			while(streamElementBuffer.isEmpty()){
    				try {
    					objectLock.wait();
    				} catch (InterruptedException e) {
    					logger.error( e.getMessage( ) , e );
    				}
    			}
    		}
			try{
				int nextInt = RandomUtils.nextInt(maximumDelay);
				Thread.sleep(nextInt);
//				System.out.println("next delay : " + nextInt + "   -->    buffer size : " + streamElementBuffer.size());
			}catch(InterruptedException e){
				logger.error( e.getMessage( ) , e );
			}
			
			if(!streamElementBuffer.isEmpty()){
				StreamElement nextStreamElement = (StreamElement)streamElementBuffer.remove();
				postStreamElement(nextStreamElement);
			}
		}
    }
  }
  
  public  DataField [] getOutputFormat ( ) {
    return collection;
  }
  
  public void actionPerformed ( ActionEvent actionEvent ) {
    StreamElement streamElement = new StreamElement( EMPTY_FIELD_LIST , EMPTY_FIELD_TYPES , EMPTY_DATA_PART , actionEvent.getWhen( ) );
    if(delayPostingElements){
    	streamElementBuffer.add(streamElement);
    	synchronized(objectLock){
    		objectLock.notifyAll();
    	}
    }
    else
    	postStreamElement( streamElement );
  }
  
  public void finalize ( ) {
    timer.stop( );
    threadCounter--;
  }
  
  public String getWrapperName() {
    return "System Time";
  }
  public int getTimerClockPeriod() {
    return timer.getDelay();
  }

  @Override
  public boolean isUsingSystemTimestamp() {
	  return false;
  }
  
  
}
