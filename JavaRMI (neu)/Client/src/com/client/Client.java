package com.client;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import com.interf.BallImpl;
import com.interf.Constant;
import com.interf.BallHolder;



public class Client {

	public static void main (String args[]) throws RemoteException, NotBoundException
		{
			Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT); //localhost: portnr als int
			BallHolder ballHolder = (BallHolder) registry.lookup(Constant.RMI_ID); //lookup-option: assign statement to new local variable (Ctrl+2,L) --> Rem aus com.interf verlinkt
			// System.out.println(remote.isLoginValid("ak")); //ak(already know?) -'username' --> "We know that"
			// System.out.println(remote.isLoginValid("test")); //wieder 'username'
			
			
			// JsonServerImpl jsonServer = new JsonServerImpl();
			JsonServer jsonServer = new JsonDummy();
			
			
			while (true) {
				try {
					String jsonBall = jsonServer.receiveBall();
					if( null != jsonBall) {
						BallImpl javaBall = BallConversion.ConvertJsonToJava(jsonBall);
						javaBall.hopCount++;
						Thread.sleep(javaBall.holdTime * 1000);
						
						// Payload behandeln
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

	
