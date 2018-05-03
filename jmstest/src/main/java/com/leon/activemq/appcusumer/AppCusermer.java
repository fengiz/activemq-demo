package com.leon.activemq.appcusumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2018/4/27.
 */
public class AppCusermer {
    private static final String MQ_URL="tcp://127.0.0.1:61616";

    private static final String MQ_NAME="test_mq3";

    private static final String MQ_NAME1="test_mq2";

    public static void main(String[] args) throws JMSException {

        // 1 创建connectionFactory
        ConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        //2. 创建connection
        Connection connection = activeMQConnectionFactory.createConnection();

        //3. 开启连接
        connection.start();

        //4. 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建目标
        Destination destination = session.createQueue(MQ_NAME);

        Queue queue = session.createQueue(MQ_NAME1);
        //6 创建消息消费者
        MessageConsumer consumer = session.createConsumer(destination);



        //创建消息监听
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("接受消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });



//        8 关闭连接
//        connection.close();
    }
}
