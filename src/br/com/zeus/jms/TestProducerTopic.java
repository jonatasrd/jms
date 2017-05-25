package br.com.zeus.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestProducerTopic {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = cf.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) context.lookup("loja");

		MessageProducer producer = session.createProducer(topic);
		Message msg = session.createTextMessage("<pedido><id> 2222 </id></pedido>");
		producer.send(msg);

		session.close();
		connection.close();
		context.close();
	}

}
