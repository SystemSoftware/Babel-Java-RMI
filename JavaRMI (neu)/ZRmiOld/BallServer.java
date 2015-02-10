package comtec.services;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

/**
 * Rmi Server, that provides the BallHolder Objekt to reveice and send balls.
 * 
 * @author Blank
 *
 */
public class BallServer {

	public static final String urlName = "rmi://localhost/comtec/BallHolderImpl";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			boolean useSecurityManager = false;
			
			if(useSecurityManager) {
				System.setSecurityManager(new RMISecurityManager());
				 
				BallHolderImpl ballHolder = new BallHolderImpl();	
				Naming.rebind(urlName, ballHolder);
			}
			else {
			
			    //LocateRegistry.createRegistry( Registry.REGISTRY_PORT );
			    LocateRegistry.createRegistry( 2050 );
	
			    BallHolderImpl ballHolder = new BallHolderImpl();
			    BallHolder stub = ballHolder;
			    		/*(BallHolder) UnicastRemoteObject.exportObject( ballHolder, 2050 );*/
			    RemoteServer.setLog( System.out );
	
			    Registry registry = LocateRegistry.getRegistry();
			    registry.rebind( "BallHolder", stub );
			    
			}
			 
			System.out.println("Server is waiting for the ball");
		}catch (Exception e) {
			System.out.println("Server failure: " + e);
		}
	}
}
