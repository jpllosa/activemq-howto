package example;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class PublishMessage {

	// make sure ActiveMQ is running
	// run SubscribeForMessage
	// check ActiveMQ, http://localhost:8161
	// run PublishMessage
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

		Context context = new InitialContext(properties);
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("youtube channel");

		System.out.println("sending...");
		MessageProducer producer = session.createProducer(topic);
		TextMessage message = session.createTextMessage();
		message.setText("https://www.youtube.com/channel/UCXcCmh1sLiq2mjkJEO4FPyA");
		producer.send(message);
		System.out.println("message sent...");

		producer.close();
		session.close();
		connection.close();
		context.close();
	}
}
