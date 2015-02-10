package com.client;

public class JsonDummy implements JsonServer{

	@Override
	public String receiveBall() throws Exception {
		Thread.sleep(2000);
		return json;
	}
		
		String json = "{\"id\": \"Ball 1\", \"hold-time\": 1, \"hop-count\": 0, \"payload\": {\"JsonObjekt\": 100, \"JavaBeans\": 120}}";
	
}
