package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Message;
import rmi.MessageImpl;
import rmi.SiteItf;

/**
 * That allow us to create the message and this message is forwarding between all sites.
 */
public class SendMessage {

	public static void main(String[] args) {
		Registry registry;
		SiteItf site;
		String message;
		String siteName;
		
		try {
			message = args[0];
		} catch (final Exception e) {
			message = "Default message";
		}		
		
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		} catch (RemoteException e) {
			throw new RuntimeException("Impossible to find registry", e);
		}
		
		try {
			siteName = args[1];
		} catch (final Exception e) {
			try {
				siteName = registry.list()[0];
			} catch (final Exception e1) {
				siteName = "0";
			}
		}
		
		try {
			site = (SiteItf) registry.lookup(siteName);
			sendMessage(site, message);
			System.out.println("Message \"" + message + "\" is sent to [" + site.getName() + "]");
		} catch (Exception e) {
			throw new RuntimeException("Site " + siteName + " not found", e);
		}
	}

    /**
     * That allow a site to forward the message to another site.
     * @param site : Site that you want
     * @param message : the message that you want to broadcast
     */
	private static void sendMessage(final SiteItf site, final String message) {
		final Message m = new MessageImpl(message, site);
		try {
			site.sendMessage(m);
		} catch (final Exception e) {
			throw new RuntimeException("Impossible to send message");
		}
	}
}