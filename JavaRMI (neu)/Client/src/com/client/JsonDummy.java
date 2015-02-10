package com.client;

public class JsonDummy implements JsonServer{

	@Override
	public String receiveBall() throws Exception {
		// TODO Auto-generated method stub
		//return null;
		return json;
	}
		
		String json = "{\"id\": \"Ball 1\", \"hold-time\": 1, \"hop-count\": 5, \"payload\": {\"Soap-Dings\": 100, \"JavaBeans\": 120}}";	
	
}
