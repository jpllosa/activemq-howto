package example;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class ReceiveMessageFromQueue {
	
	// make sure ActiveMQ is running
	// run SendMessageToQueue
	// check ActiveMQ, http://localhost:8161
	// run ReceiveMessageFromQueue
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

		Context context = new InitialContext(properties);
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("youtube channel");
		MessageConsumer consumer = session.createConsumer(queue);
		connection.start();
		
		Message message = consumer.receive(3000L);
		if (message instanceof TextMessage) {
			System.out.println("visit: " + ((TextMessage) message).getText());
		} else {
			System.out.println("other message: " + message);
		}

		connection.stop();
		consumer.close();
		session.close();
		connection.close();
		context.close();
	}
}
