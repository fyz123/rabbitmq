package com.rabbitmq.direct;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs direct";
        String routeKey = "error";

        channel.exchangeDeclare(exchangeName,"direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,exchangeName,routeKey);

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
