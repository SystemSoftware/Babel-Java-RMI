package com.client;

/**
 * Allows the communication with the json server.
 */
public interface JsonServer {
	
	/**
	 * Receives the ball from the JSON-Server.
	 * @return A JSON-String, if the ball is available, else null.
	 * @exception If the server is not available.
	 */
	String receiveBall() throws Exception;
	
}
