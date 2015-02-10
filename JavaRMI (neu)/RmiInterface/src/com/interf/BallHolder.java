package com.interf;

import java.rmi.*;

/**
 * Provides methods for the Rmi service object, that holds the balls that have been received.
 * @author Blank
 *
 */
public interface BallHolder extends Remote {
	boolean isBallAvailable() throws RemoteException;
	Ball receiveBall() throws RemoteException;
	void sendBall(Ball ball) throws RemoteException;
}
