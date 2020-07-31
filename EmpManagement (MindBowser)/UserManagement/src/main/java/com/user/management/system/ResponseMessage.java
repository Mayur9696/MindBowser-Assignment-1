package com.user.management.system;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {

	public static Map<String, Object> message(String message,int code,boolean status) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Code", code);
		map.put("Status", status);
		map.put("Message", message);
		return map;
		
	}
	
	public static Map<String, Object> message(String message,int code,boolean status,Object data) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Code", code);
		map.put("Status", status);
		map.put("Message", message);
		map.put("Data", data);
		return map;
		
	}
}
