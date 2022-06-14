package com.lgcns.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CreateServlet extends GeneralServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(req.getInputStream()));
		Gson gson = new Gson();
		RequestVO requestVO = gson.fromJson(jsonElement.toString(), RequestVO.class);
		
		int queueSize = requestVO.getQueueSize();
		System.out.println(queueSize);
		String queueName = req.getRequestURI().substring(8);
		System.out.println(queueName);
		if (queueMap.containsKey(queueName)) {
			res.getWriter().write("{\"Result\": \"Queue Exist\"}");
		} else {
			LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<String>(queueSize);
			queueMap.put(queueName, queue);
			res.getWriter().write("{\"Result\": \"Ok\"}");
		}
		res.setStatus(200);
	}
}
