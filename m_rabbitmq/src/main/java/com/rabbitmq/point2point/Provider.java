package com.rabbitmq.point2point;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/** 生产者 */
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);

        channel.basicPublish("","hello",null, UUID.randomUUID().toString().getBytes());
        RabbitmqUtil.closeConnection(channel,connection);
    }
}
