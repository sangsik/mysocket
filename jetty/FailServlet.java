package com.lgcns.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FailServlet extends GeneralServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);

		String queueName = req.getRequestURI().substring(6, req.getRequestURI().lastIndexOf('/'));
		String messageId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')+1);
		System.out.println("FailServlet:" + queueName);
		String message = messageMap.remove(messageId);
		System.out.println("FAIL: " + messageId + ":" + message);
		//String message = queueMap2.get(queueName).poll();
		queueMap.get(queueName).addFirst(message);
		res.getWriter().write("{\"Result\": \"Ok\"}");
	}
}
