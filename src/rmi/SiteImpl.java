package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SiteImpl extends UnicastRemoteObject implements SiteItf, Serializable {

	private static final long serialVersionUID = -8341533288596431542L;
	
	private String name;
    private List<SiteItf> children;
    private final List<Message> receivedMessages;

    /**
     * The default constructor of the site
     * @param name : the name of the site
     * @throws RemoteException
     */
    public SiteImpl(String name) throws RemoteException {
    	this.children = new ArrayList<SiteItf>();
    	this.name = name;
    	this.receivedMessages = new ArrayList<Message>();
        System.out.println("[" + this.name + "] is logged");
    }

    @Override
    public synchronized void sendMessage(final Message message) throws RemoteException {
    	
    	System.out.println("[" + this.name + "] receives \"" + message.getContent()
    			+ "\" from " + message.getSender().getName());
    	
		synchronized (this.receivedMessages) {
			if (this.receivedMessages.contains(message)) {
				// Message already transferred !
				return;
			}
			this.receivedMessages.add(message);
		} 	
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Transmit to all connected children
				for (final SiteItf child : SiteImpl.this.children) {
					new Thread(new Runnable() {
						final SiteItf c = child;

						@Override
						public void run() {
							try {
								System.out.println("[" + SiteImpl.this.name
										+ "] transfer \""
										+ message.getContent() + "\" to "
										+ this.c.getName());
								this.c.sendMessage(message);
							} catch (final RemoteException e) {
								throw new RuntimeException(
										"Unable to send message to site", e);
							}
						}
					}).run();
				}
			}
		}).run();
    }
    
    @Override
    public void addChild(final SiteItf child) throws RemoteException {
    	if (!this.children.contains(child)) {
    		this.children.add(child);
    		System.out.println("[" + this.name + "] connected with "
					+ child.getName());
    	}
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    /**
     * An accessor to get only one child
     * @param name : name of the child
     * @return the child or null if not existing
     * @throws RemoteException
     */
    protected SiteItf getChild(String name) throws RemoteException {
        for (SiteItf child : children)
            if (child.getName().equals(name)) {
                return child;
            }
        return null;
    }
}