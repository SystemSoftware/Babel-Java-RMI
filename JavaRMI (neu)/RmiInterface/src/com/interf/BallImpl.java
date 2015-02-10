package com.interf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BallImpl implements Ball, Serializable, Comparable<BallImpl> {

	private static final long serialVersionUID = 6461787888504759041L;
	
	public String id;
	public int holdTime;
	public int hopCount;
	public Map<String, String> payLoad = new HashMap<String,String>();	
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getHoldTime() {
		return holdTime;
	}

	@Override
	public int getHopCount() {
		return hopCount;
	}

	@Override
	public Map<String, String> getPayload() {
		return payLoad;
	}
	
	@Override
	public void increaseHopCount() {
		this.hopCount++;
	}

	@Override
	public int compareTo(BallImpl arg0) {
		return this.hashCode() - arg0.hashCode();
	}
}
