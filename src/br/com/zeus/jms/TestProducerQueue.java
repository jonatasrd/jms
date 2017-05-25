package br.com.zeus.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestProducerQueue {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = cf.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");
		MessageProducer producer = session.createProducer(fila);

		for (int i = 0; i < 1000; i++) {
			Message msg = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
			producer.send(msg);
		}

		session.close();
		connection.close();
		context.close();
	}

}
