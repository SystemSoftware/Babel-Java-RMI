package com.client;

import java.net.HttpURLConnection;
import java.net.URL;

import com.interf.Ball;

public class JsonServerImpl implements JsonServer {

	String serverURL;
	
	
	public boolean isBallAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String receiveBall() throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL(serverURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		// con.setRequestProperty("", "");
		
		String jsonString = null;
		if( con.getResponseCode() == 200) {

			
			jsonString = con.getResponseMessage();
			
					
		}
		return jsonString;	
	}


	public void sendBall(Ball ball) {
		// TODO Auto-generated method stub
		
	}

}
