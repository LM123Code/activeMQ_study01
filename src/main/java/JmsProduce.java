import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author LM_Code
 * @create 2019-09-14-15:43
 */
public class JmsProduce {
    private static final String ACTIVEMQ_URL = "tcp://101.132.78.78:61616";
    public static void main(String[] args) throws JMSException {
        //1创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过工厂获取连接connection,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3创建会话session,两个参数事务与签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，队列还是主题
        Queue queue = session.createQueue("queue01");
        //5.创建消息的生产者，确定往哪个队列里发送
        MessageProducer producer = session.createProducer(queue);
        //6.通过生产者发送消息到队列
        for (int i = 0; i < 3; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("msg----" + i);
            //8.通过消息生产者发送给mq
            producer.send(textMessage);
        }
        //释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("*********消息发布到MQ*********");
    }
}
