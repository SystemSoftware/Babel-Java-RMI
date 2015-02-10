package com.interf;
import java.rmi.Remote;
import java.rmi.RemoteException; 
public interface Rem extends Remote 
	{
		public boolean isLoginValid (String username) throws RemoteException; //without Exception won't work
}
