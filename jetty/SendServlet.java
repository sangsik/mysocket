package com.lgcns.test;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SendServlet extends GeneralServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(req.getInputStream()));
		Gson gson = new Gson();
		RequestVO requestVO = gson.fromJson(jsonElement.toString(), RequestVO.class);
		
		String message = requestVO.getMessage();
		System.out.println(message);
		
		String queueName = req.getRequestURI().substring(6);
		
		try {
			queueMap.get(queueName).add(message);
			res.getWriter().write("{\"Result\": \"Ok\"}");
		} catch (IllegalStateException e) {
			res.getWriter().write("{\"Result\": \"Queue Full\"}");
		}
		
		res.setStatus(200);
	}
}
