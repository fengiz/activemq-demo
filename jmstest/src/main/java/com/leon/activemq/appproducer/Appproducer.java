package com.leon.activemq.appproducer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.swing.border.EtchedBorder;

/**
 * Created by Administrator on 2018/4/27.
 */
public class Appproducer {
    private static final String MQ_URL="tcp://127.0.0.1:61616";

    private static final String MQ_NAME="test_mq3";

    private static final String MQ_NAME1="test_mq3";

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
        //6 创建消息生产者
        MessageProducer producer = session.createProducer(destination);

        MessageProducer producer1 = session.createProducer(destination);

        MessageProducer producer2 = session.createProducer(queue);

        for(int i =0;i<100;i++){
            //7 创建消息
            TextMessage textMessage = session.createTextMessage("hello"+i);

            producer.send(textMessage);

            System.out.println("发送消息>>>"+ textMessage.getText()+"成功");
        }
        //8 关闭连接
        connection.close();
    }
}
