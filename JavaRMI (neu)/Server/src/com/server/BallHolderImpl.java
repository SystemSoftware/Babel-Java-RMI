package com.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.PriorityQueue;
import java.util.Queue;

import com.interf.Ball;
import com.interf.BallHolder;

public class BallHolderImpl extends UnicastRemoteObject implements BallHolder {

	private static final long serialVersionUID = 9089063237844923832L;

	protected BallHolderImpl() throws RemoteException {
		super();
		balls = new PriorityQueue<Ball>();
	}

	@Override
	public boolean isBallAvailable() {
		return (balls.size() > 0);
	}

	@Override
	public Ball receiveBall() {
		return balls.poll();
	}

	@Override
	public void sendBall(Ball ball) {
		ball.increaseHopCount();
		balls.add(ball);
	}
	
	Queue<Ball> balls;
}
