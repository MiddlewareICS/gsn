package gsn.wrappers.backlog.plugins;


import java.io.Serializable;

import javax.naming.OperationNotSupportedException;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.wrappers.BackLogWrapper;


/**
 * The PluginInterface specifies the functionality a plugin used by
 * {@link BackLogWrapper} has to offer.
 * 
 * @author Tonio Gsell
 */
public abstract class AbstractPlugin {
	
	public final int PACKET_PROCESSED = 0;
	public final int PACKET_SKIPPED = 1;
	public final int PACKET_ERROR = 2;
	
	private BackLogWrapper activeBackLogWrapper = null;

	/**
	 * Initialize the plugin.
	 * <p>
	 * This function will be called once at the initialization time
	 * of GSN by the {@link BackLogWrapper}. It has to be called by
	 * the plugins initialize function!
	 * 
	 * @param backLogWrapper points to the calling
	 * 			BackLogWrapper. It can be used
	 * 			to access its functionality.
	 * 
	 * @return true if the initialization was successful otherwise
	 * 			 false
	 */
	public boolean initialize ( BackLogWrapper backLogWrapper ) {
		activeBackLogWrapper = backLogWrapper;
		return true;
	}


	/**
	 * Stop the plugin.
	 */
	public void stop() { }


	/**
	 * This function is called if a packet has been received this
	 * plugin is interested in.
	 * 
	 * @param timestamp when this packet has been generated.
	 *			It should be used to acknowledge this packet if
	 *			needed (use {@code gsn.wrappers.BackLogWrapper.ackMessage(long)}).
	 *
	 * @param packet as byte array
	 * 
	 * @return  PACKET_PROCESSED if successfully processed
     *			PACKET_SKIPPED 	 if no action was taken
	 *			PACKET_ERROR	 if there was an error
	 */
	public abstract int packetReceived ( long timestamp, byte[] packet );


	/**
	 * With this function any command can be sent to the plugin.
	 * <p>
	 * A virtual sensor can send a command to this plugin using
	 * {@link BackLogWrapper#sendToWrapper(String, String[], Object[]) sendToWrapper},
	 * which will just forward the command to the plugin.
	 * 
	 * This can be either a command directed at the plugin itself
	 * or a command which can be used to send something to the
	 * remote sensor.
	 * 
	 * @param action the action name
	 * @param paramNames the name of the different parameters
	 * @param paramValues the different parameter values
	 * 
	 * @return true if the plugin could successfully process the
	 * 			 data otherwise false
	 * @throws OperationNotSupportedException 
	 */
	public boolean sendToPlugin ( String action , String [ ] paramNames , Object [ ] paramValues ) throws OperationNotSupportedException {
		throw new OperationNotSupportedException( "This plugin doesn't support sending data back to the source." );
	}


	/**
	 * With this function any object can be sent to the plugin.
	 * <p>
	 * A virtual sensor can send an object to this plugin using the
	 * {@link BackLogWrapper#sendToWrapper(Object) sendToWrapper}, which
	 * will just forward the object to the plugin.
	 * 
	 * This can be either an object directed at the plugin itself
	 * or an object which can be used to send something to the
	 * remote sensor.
	 * 
	 * @param dataItem to be processed
	 * 
	 * @return true if the plugin could successfully process the
	 * 			 data otherwise false
	 */
	public boolean sendToPlugin(Object dataItem) throws OperationNotSupportedException {
		throw new OperationNotSupportedException( "This plugin doesn't support sending data back to the source." );
	}


	/**
	 * Get the {@link gsn.wrappers.backlog.BackLogMessage BackLogMessage} type this plugin is using.
	 * <p>
	 * This function should be implemented as following:
	 * <ul>
	 *  public byte getMessageType() {
	 *  <ul>
	 *   return gsn.wrappers.backlog.BackLogMessage.<i>MESSAGENAME</i>_MESSAGE_TYPE;
	 *  </ul>
	 *  }
	 * </ul>
	 * 
	 * where <i>MESSAGENAME</i> should be a unique name of the plugin.
	 * 
	 * <i>MESSAGENAME</i>_MESSAGE_TYPE has to be implemented and documented in
	 * BackLogMessage.
	 * <p>
	 * @return the message type
	 */
	public abstract byte getMessageType();


	/**
	 * This function is used to specify the output structure of
	 * the data this plugin produces.
	 * <p>
	 * The output structure must agree with the data produced by
	 * this plugin, meaning that the data passed to
	 * {@link BackLogWrapper#dataProcessed(long, java.io.Serializable...) dataProcessed} must
	 * agree with it.
	 * <p>
	 * For further information about the output structure please
	 * refer to GSN-Wrapper's <i>getOutputFormat</i> documentation.
	 * 
	 * @return the output structure of the plugin in a DataField
	 */
	public abstract DataField[] getOutputFormat();




	/**
	 * This function can be called by the plugin, if it has processed
	 * the received data from the deployment.
	 * The data will be forwarded to the corresponding
	 * virtual sensor by GSN and will be put into the database.
	 * <p>
	 * The data format must correspond to the one specified by
	 * the plugin's getOutputFormat() function.
	 * 
	 * @param timestamp
	 * 			The timestamp in milliseconds this data has been
	 * 			generated.
	 * @param data 
	 * 			The data to be processed. Its format must correspond
	 * 			to the one specified by the plugin's getOutputFormat()
	 * 			function.
	 * @return false if storing the new item fails otherwise true
	 */
	public boolean dataProcessed(long timestamp, Serializable... data) {
		return activeBackLogWrapper.dataProcessed(timestamp, data);
	}



	/**
	 * This function can be called by the plugin, if it has processed
	 * the data received from GSN or on any other occasion which asks
	 * for sending data to the deployment.
	 * 
	 * 
	 * @param timestamp
	 * 			The timestamp in milliseconds this data has been
	 * 			generated.
	 * @param data 
	 * 			The data to be processed. Its format must correspond
	 * 			to the one specified by the plugin's getOutputFormat()
	 * 			function.
	 * @return if the message has been sent successfully true will be returned
	 * 			 else false (no working connection)
	 */
	public boolean sendRemote(byte[] data) {
		return activeBackLogWrapper.sendRemote(data);
	}




	/**
	 * This function must be called by the plugin, to acknowledge
	 * incoming messages if it is using the backlog functionality
	 * on the deployment side.
	 * 
	 * The timestamp will be used at the deployment to remove the
	 * corresponding message backloged in the database. If messages
	 * are not acknowledged by this plugin but its counterpart on
	 * the deployment side will backlog them, we endanger to
	 * overflow the backlog database!
	 * 
	 * @param timestamp
	 * 			The timestamp is used to acknowledge a message. Thus
	 * 			it has to be equal to the timestamp from the received
	 * 			message we want to acknowledge.
	 */
	public void ackMessage(long timestamp) {
		activeBackLogWrapper.ackMessage(timestamp);
	}
	
	
	/**
	 * Retruns true if the deploymentClient is connected to the deployment.
	 * 
	 * @return true if the client is connected otherwise false
	 */
	public boolean isConnected() {
		return activeBackLogWrapper.isConnected();
	}
	
	
	public final AddressBean getActiveAddressBean ( ) {
		return activeBackLogWrapper.getActiveAddressBean();
	}
	

	public boolean isTimeStampUnique() {
	  return activeBackLogWrapper.isTimeStampUnique();
	}
}
