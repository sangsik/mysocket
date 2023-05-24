package com.lgcns.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		doService(req, res, HttpMethod.GET);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		doService(req, res, HttpMethod.POST);

	}

	private void doService(HttpServletRequest req, HttpServletResponse res, HttpMethod getOrPost) throws IOException {

		//로그파일 생성
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("LOG.TXT"), true));
		bw.write(req.getHeader("x-requestId") + "#" + req.getRemoteAddr() + ":" + req.getServerPort() + req.getRequestURI());
		bw.write("\n");
		bw.flush();
		bw.close();

		
		System.out.println("x-requestId: " + req.getHeader("x-requestId"));

		String requestURI = req.getRequestURI();
		
		if (requestURI.startsWith("/trace")) {
		
			//수집로그로부터 응답생성
		}
		
		Map<String, String[]> paramMap = req.getParameterMap();

		String targetUrl = "";
		for (Route route : RunManager.routeInfo.routes) {
			if (requestURI.startsWith(route.getPathPrefix())) {
				targetUrl = route.getUrl();
				System.out.println(requestURI + " match url: " + targetUrl);
			}
		}

		HttpClient httpClient = new HttpClient();
		try {
			httpClient.start();
			Request newRequest = httpClient.newRequest(targetUrl).method(getOrPost).path(requestURI);
			for (String key : paramMap.keySet()) {
				newRequest.param(key, paramMap.get(key)[0]);
				System.out.println("key=" + key + ", value=" + paramMap.get(key)[0]);
			}
			ContentResponse cres = newRequest.header("x-requestId", req.getHeader("x-requestId")).send();
			res.getWriter().write(cres.getContentAsString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}