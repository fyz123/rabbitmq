package com.rabbitmq.topic;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics","topic");
        channel.basicPublish("topics","user",null,"topic msg".getBytes());
        RabbitmqUtil.closeConnection(channel,connection);
    }
}
