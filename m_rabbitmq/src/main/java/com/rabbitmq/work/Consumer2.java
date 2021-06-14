package com.rabbitmq.work;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);
        channel.basicQos(1);

        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2：" + new String(body) );
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
