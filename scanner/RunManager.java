import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class RunManager {

	private static HashMap<String, Queue<String>> queueMap = new HashMap<String, Queue<String>>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if (input.startsWith("CREATE ")) {
				String[] tokens = input.split(" ");
				String queueName = tokens[1];
				int queueSize = Integer.valueOf(tokens[2]);
				
				if (queueMap.containsKey(queueName)) {
					System.out.println("Queue Exist");
				} else {
					Queue<String> queue = new LinkedBlockingQueue<String>(queueSize);
					queueMap.put(queueName, queue);
				}
			}
			else if (input.startsWith("SEND ")) {
				String[] tokens = input.split(" ");
				String queueName = tokens[1];
				String message = tokens[2];
				try {
					queueMap.get(queueName).add(message);
				} catch (IllegalStateException e) {
					System.out.println("Queue Full");
				}
			} else if (input.startsWith("RECEIVE ")) {
				String queueName = input.substring(8);
				System.out.println(queueMap.get(queueName).poll());
			}
		}
	}

}
