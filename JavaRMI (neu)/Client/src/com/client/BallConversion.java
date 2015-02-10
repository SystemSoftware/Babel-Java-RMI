package com.client;

import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.interf.Ball;
import com.interf.BallImpl;

/**
 * Converts to ball data, so that it can be communicated between different servers.
 *
 */
public class BallConversion {

	public static void main(String[] args) {
		try {
			testConversion();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void testConversion() throws ParseException {
		String json = "{\"id\": \"Ball 1\", \"hold-time\": 1, \"hop-count\": 5, \"payload\": {\"Soap-Dings\": 100, \"JavaBeans\": 120}}";
		
		Ball ball = ConvertJsonToJava(json);
		
		System.out.println(ball);
	}
	
	/**
	 * Converts a json ball structure to a java object.
	 * @param jsonBall The json ball data
	 * @return The ball as java object
	 * @throws ParseException
	 */
	public static BallImpl ConvertJsonToJava(String jsonBall) throws ParseException {
		JSONParser parser = new JSONParser();
		
		BallImpl ball = new BallImpl();
		
		Object obj = parser.parse(jsonBall);
		JSONObject array = (JSONObject)obj;
		
		String obj2 = (String)array.get("id");
		Long obj3 = (Long)array.get("hold-time");
		Long obj4 = (Long)array.get("hop-count");
		Object obj5 = array.get("payload");
		
		
		ball.id = (String)obj2;
		ball.holdTime = (int)(long) obj3;
		ball.hopCount = (int)(long) obj4;
		
		JSONObject serverMap = (JSONObject)obj5;
		for(Object o : serverMap.entrySet()) {
			Entry<?, ?> e = (Entry<?, ?>)o;
			ball.payLoad.put(e.getKey().toString(), e.getValue().toString());
		}
		
		return ball;
	}
}
