package main;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.SiteImpl;
import rmi.SiteItf;

/**
 * Create the server to simulate a tree with 6 sites
 *
 * 1 -|- 2 -|- 3
 *    |     |- 4
 *    |
 *    |- 5 --- 6
 */
public class TreeServer {
    
    public static void main(String[] args) {
    	Registry registry;
    	SiteItf site1, site2, site3, site4, site5, site6;
    	
    	try {
			registry = LocateRegistry.createRegistry(1099);
			System.out.println("The server is started on the port 1099 (default)");
		} catch (RemoteException e) {
			throw new RuntimeException("Impossible to start server", e);
		}
		
    	try {
    		site1 = new SiteImpl("site1");
    		site2 = new SiteImpl("site2");
    		site3 = new SiteImpl("site3");
    		site4 = new SiteImpl("site4");
    		site5 = new SiteImpl("site5");
    		site6 = new SiteImpl("site6");
    		
    		site1.addChild(site2);
    		site1.addChild(site5);
    		site2.addChild(site3);
    		site2.addChild(site4);		
    		site5.addChild(site6);
    		
    		registry.rebind(site1.getName(), site1);
    		registry.rebind(site2.getName(), site2);
    		registry.rebind(site3.getName(), site3);
    		registry.rebind(site4.getName(), site4);
    		registry.rebind(site5.getName(), site5);
    		registry.rebind(site6.getName(), site6);	
    		
		} catch (AccessException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException("Unable to connect to RMI server", e);
		}	
	}
}