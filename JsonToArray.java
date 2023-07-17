package com.lgcns.test;

import java.io.File;
import java.io.FileReader;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonToArray {

	public static RouteInfo[] routeInfo;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File routingFile = new File("Array.json");
		JsonElement jsonElement = JsonParser.parseReader(new FileReader(routingFile));
		Gson gson = new Gson();
		routeInfo = gson.fromJson(jsonElement.toString(), RouteInfo[].class);
		
		int port = routeInfo[0].getPort();
		
		System.out.println(port);
	}

}
