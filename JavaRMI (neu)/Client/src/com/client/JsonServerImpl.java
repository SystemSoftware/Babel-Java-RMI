package com.client;

import java.net.HttpURLConnection;
import java.net.URL;

import com.interf.Constant;

public class JsonServerImpl implements JsonServer {

	String serverURL;
	
	/**
	 * Receives the ball from the JSON-Server.
	 * @return A JSON-String, if the ball is available, else null.
	 * @exception If the server is not available.
	 */
	public String receiveBall() throws Exception {
		URL url = new URL(Constant.JSON_SERVER_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		String jsonString = null;
		if( con.getResponseCode() == 200) {

			
			jsonString = con.getResponseMessage();
			
					
		}
		return jsonString;	
	}

}
