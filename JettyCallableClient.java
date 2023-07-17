package com.lgcns.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;

public class JettyCallableClient {
	public static void main(String[] args) throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		long start = System.currentTimeMillis();

		MyCallable callable1 = new MyCallable("0001");
		Future<String> future1 = executorService.submit(callable1);

		MyCallable callable2 = new MyCallable("1001");
		Future<String> future2 = executorService.submit(callable2);

		System.out.println(future1.get());
		System.out.println(future2.get());

		long end = System.currentTimeMillis();
		System.out.println("elapsed : " + (end - start));

		executorService.shutdown();
	}
}

class MyCallable implements Callable<String> {

	private String requestId;

	public MyCallable(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		String inputQueueURI = "http://127.0.0.1:5001/front";
		httpClient.start();
		ContentResponse contentRes = httpClient.newRequest(inputQueueURI).header("x-requestId", requestId)
				.method(HttpMethod.GET).send();
		Gson gson = new Gson();
		ResponseData response = gson.fromJson(contentRes.getContentAsString(), ResponseData.class); // 데이터를 읽어서 Response

		System.out.println(contentRes.getContentAsString());
		System.out.println("result :" + response.getResult() + ", status :" + response.getStatus());
		System.out.println(contentRes.getHeaders().get("x-responseId"));
		
		httpClient.stop();
		
		
		return contentRes.getContentAsString();
	}

}
