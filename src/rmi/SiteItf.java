package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface of a site which will be connected with others sites.
 */
public interface SiteItf extends Remote {

	/**
	 * Add a connection to the current node
	 * @param child The node connection
	 * @throws RemoteException
	 */
    public void addChild(final SiteItf child) throws RemoteException;

    /**
     * Send a message to all connected nodes unless the message is already sent
     * @param message The message to be sent
     * @throws RemoteException
     */
    public void sendMessage(final Message message) throws RemoteException;

    /**
     * Get the name of the current node
     * @return name The name of the current node
     * @throws RemoteException
     */
    public String getName() throws RemoteException ;
}