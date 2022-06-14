import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AckServlet extends GeneralServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);

		String queueName = req.getRequestURI().substring(5, req.getRequestURI().lastIndexOf('/'));
		String messageId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')+1);
		System.out.println("AckServlet:" + queueName);
		
		//String message = queueMap2.get(queueName).poll();
		String message = messageMap.remove(messageId);

		System.out.println("ACK: " + messageId + ":" + message);

		//queueMap.get(queueName).remove(message);
		res.getWriter().write("{\"Result\": \"Ok\"}");
	}
}
