package com.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.interf.Constant;

public class JsonServerImpl implements JsonServer {

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
			
			InputStream stream = con.getInputStream();
			InputStreamReader isReader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(isReader);
			jsonString = br.readLine();
			br.close();	
		}
		return jsonString;	
	}

}
