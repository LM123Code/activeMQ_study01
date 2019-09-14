import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author LM_Code
 * @create 2019-09-14-16:45
 */
public class JmsConsumer {
    private static final String ACTIVEMQ_URL = "tcp://101.132.78.78:61616";
    public static void main(String[] args) throws JMSException {
        //1.创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.生成连接并访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地
        Queue queue01 = session.createQueue("queue01");
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue01);
        while (true){
            TextMessage receive = (TextMessage) consumer.receive();
            if(receive != null){
                System.out.println("*****消费者接受到消息" + receive.getText());
            }else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
