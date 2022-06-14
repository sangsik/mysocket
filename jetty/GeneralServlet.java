package com.lgcns.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.HttpServlet;

public class GeneralServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static Map<String, LinkedBlockingDeque<String>> queueMap = new HashMap<String, LinkedBlockingDeque<String>>();
	//protected static Map<String, LinkedBlockingDeque<String>> queueMap2 = new HashMap<String, LinkedBlockingDeque<String>>();
	protected static Map<String, String> messageMap = new HashMap<String, String>(); 
}
