package com.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.interf.Constant;

public class RMIServer {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException 
	{
		BallHolderImpl ballHolder = new BallHolderImpl();
		Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		registry.bind(Constant.RMI_ID, ballHolder); 
		System.out.println("Server is started");
	}
}
