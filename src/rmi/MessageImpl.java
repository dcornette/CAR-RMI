package rmi;

public class MessageImpl implements Message {

	private final String content;
	private final SiteItf sender;

    /**
     * The default constructor of the message
     * @param content : the message that you want to send
     * @param sender : the initial transmitter of the message
     */
	public MessageImpl(final String content, final SiteItf sender) {
		this.content = content;
		this.sender = sender;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public SiteItf getSender() {
		return this.sender;
	}
}