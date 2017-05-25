package br.com.zeus.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestConsumerQueue {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = cf.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topic = (Destination) context.lookup("loja");
		MessageConsumer consumer = session.createConsumer(topic);

		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage txtMessage = (TextMessage) message;
				try {
					System.out.println("Recebendo msg: " + txtMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		new Scanner(System.in).nextLine();

		session.close();
		connection.close();
		context.close();
	}

}
