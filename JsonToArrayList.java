package com.lgcns.test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonToArrayList {

	public static ArrayList<RouteInfo> routeInfo;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File routingFile = new File("Array.json");
		JsonElement jsonElement = JsonParser.parseReader(new FileReader(routingFile));
		Gson gson = new Gson();
		routeInfo = gson.fromJson(jsonElement.toString(), new TypeToken<List<RouteInfo>>(){}.getType());
		
		int port = routeInfo.get(0).getPort();
		
		System.out.println(port);
	}

}
