import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class RunManager {

	public static void main(String[] args) throws Exception {
		new RunManager().start();
	}

	public void start() throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(8080);
		server.addConnector(http);

		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(CreateServlet.class, "/CREATE/*");
		servletHandler.addServletWithMapping(SendServlet.class, "/SEND/*");
		servletHandler.addServletWithMapping(ReceiveServlet.class, "/RECEIVE/*");
		servletHandler.addServletWithMapping(AckServlet.class, "/ACK/*");
		servletHandler.addServletWithMapping(FailServlet.class, "/FAIL/*");
		
		server.setHandler(servletHandler);

		server.start();
		server.join();
	}
}
