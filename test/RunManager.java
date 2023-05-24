package com.lgcns.test;

import java.io.File;
import java.io.FileReader;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RunManager {

	public static RouteInfo routeInfo;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File logFile = new File("LOG.TXT");
		if (logFile.exists()) {
			logFile.delete();
		}
		
		File routingFile = new File(args[0]);
		JsonElement jsonElement = JsonParser.parseReader(new FileReader(routingFile));
		Gson gson = new Gson();
		routeInfo = gson.fromJson(jsonElement.toString(), RouteInfo.class);
		int port = routeInfo.getPort();

		
    	// 1. Web Server, Server Connector 생성
		Server server = new Server();
		ServerConnector httpConector = new ServerConnector(server);
		httpConector.setHost("127.0.0.1");
		httpConector.setPort(port);
		server.addConnector(httpConector);
		
        // 2. Servlet Handler 매핑
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(MyServlet.class, "/");
		server.setHandler(servletHandler);
		
        // 3. Web Server start
		server.start();
		server.join();
	}

}
