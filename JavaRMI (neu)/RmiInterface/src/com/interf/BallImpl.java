package com.interf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BallImpl implements Ball, Serializable, Comparable<BallImpl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6461787888504759041L;
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHoldTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHopCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> getPayload() {
		// TODO Auto-generated method stub
		return null;
	}

	public String id;
	public int holdTime;
	public int hopCount;
	public Map<String, String> payLoad = new HashMap<String,String>();
	@Override
	public void increaseHopCount() {
		// TODO Automatisch generierter Methodenstub
		this.hopCount++;
	}

	@Override
	public int compareTo(BallImpl arg0) {
		// TODO Auto-generated method stub
		return this.hashCode() - arg0.hashCode();
	}
}
