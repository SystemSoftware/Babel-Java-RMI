package com.interf;

import java.util.Map;

/**
 * Represents the ball object, that is given around the server circle.
 *
 */
public interface Ball {
	String getId();
    int getHoldTime(); 
    int getHopCount();
    void increaseHopCount();
    Map<String, String> getPayload();
}
