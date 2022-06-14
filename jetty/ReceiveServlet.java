import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiveServlet extends GeneralServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(req.getInputStream()));
		//Gson gson = new Gson();
		//RequestVO requestVO = gson.fromJson(jsonElement.toString(), RequestVO.class);
		
		String queueName = req.getRequestURI().substring(9);
		System.out.println(queueName);
		if (queueMap.get(queueName).size() == 0 ) {
			res.getWriter().write("{\"Result\": \"No Message\"}");
		} else {
			String message = queueMap.get(queueName).poll();
			String messageId = String.valueOf((new Random()).nextInt());
			while (messageMap.containsKey(messageId)) {
				messageId = String.valueOf((new Random()).nextInt());
			}
			System.out.println("RECV: " + messageId + ":" + message);
			messageMap.put(messageId, message);
			res.getWriter().write("{\"Result\": \"Ok\", \"MessageID\": \"" + messageId + "\", \"Message\": \"" + message + "\"}");
		}
		
		System.out.println("PLAY:" + queueMap.get("PLAY"));
		System.out.println("LOG:" + queueMap.get("LOG"));
	}
}
