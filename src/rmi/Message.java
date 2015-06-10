package rmi;

/**
 * Interface of a message which will be sent to all sites.
 */
public interface Message {
	/**
	 * Get the content of the message
	 * @return content The content of the message
	 */
	String getContent();

	/**
	 * Get the sender of the message
	 * @return sender The sender of the message
	 */
	SiteItf getSender();
}