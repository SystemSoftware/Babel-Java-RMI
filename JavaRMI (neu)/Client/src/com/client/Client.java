package com.client;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import com.interf.BallImpl;
import com.interf.Constant;
import com.interf.BallHolder;



/**
 * Represents the client, that shares the ball between the JSON and the Java RMI service.
 */
public class Client {

	public static void main (String args[]) throws RemoteException, NotBoundException
		{
			Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT); //localhost: portnr als int
			BallHolder ballHolder = (BallHolder) registry.lookup(Constant.RMI_ID); 
			
			
			boolean useJsonDummy = true;
			JsonServer jsonServer;
			
			if (useJsonDummy) {
				jsonServer = new JsonDummy();
			} else {
				jsonServer = new JsonServerImpl();
			}
			
			
			while (true) {		// ball polling loop
				
				try {
					String jsonBall = jsonServer.receiveBall();
					if( null != jsonBall) {
						BallImpl javaBall = BallConversion.ConvertJsonToJava(jsonBall);
						
						// update ball stats here
						javaBall.hopCount++;
						Thread.sleep(javaBall.holdTime * 4000);
						
						System.out.println("Ball has had " + javaBall.hopCount + " contacts");
						
						// payload calculation
						// Wenn noch kein Zeitstempel --> aktuelle Zeit
						Date d = new Date();	
						long currentTime = d.getTime();							
						if(javaBall.payLoad.containsKey(Constant.RMI_SERVER_KEY)) {
							String lastTimeString = javaBall.payLoad.get(Constant.RMI_SERVER_KEY);
							long roundTime;
							long lastTime = Long.parseLong(lastTimeString);
							roundTime = currentTime - lastTime;
							System.out.println("Received the ball after " + roundTime + "MS");
						}
				
						javaBall.payLoad.put(Constant.RMI_SERVER_KEY, currentTime + "");
						
						
						
						ballHolder.sendBall(javaBall);
						System.out.println("Ball has been sent to server!");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
}

	
