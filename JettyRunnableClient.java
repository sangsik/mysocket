package com.lgcns.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class JettyRunnableClient {
	public static void main(String[] args) throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		long start = System.currentTimeMillis();

		MyRunnable callable1 = new MyRunnable("0001");
		executorService.submit(callable1);

		MyRunnable callable2 = new MyRunnable("1001");
		executorService.submit(callable2);

		executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
		
		long end = System.currentTimeMillis();
		System.out.println("elapsed : " + (end - start));

	}
}

class MyRunnable implements Runnable {

	private String requestId;

	public MyRunnable(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public void run() {
		try {
			// TODO Auto-generated method stub
			HttpClient httpClient = new HttpClient();
			String inputQueueURI = "http://127.0.0.1:5001/front";
			httpClient.start();
			ContentResponse contentRes = httpClient.newRequest(inputQueueURI).header("x-requestId", requestId)
					.method(HttpMethod.GET).send();
//			Gson gson = new Gson();
//			Response response = gson.fromJson(contentRes.getContentAsString(), Response.class); // 데이터를 읽어서 Response

			System.out.println(contentRes.getContentAsString());
			System.out.println(contentRes.getHeaders().get("x-responseId"));
			httpClient.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
