package com.rabbitmq.direct;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        String exchangeName = "logs direct";
        String routeKey = "error";

        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName,"direct");

        channel.basicPublish(exchangeName,routeKey,null,"direct msg".getBytes());
        RabbitmqUtil.closeConnection(channel,connection);
    }
}
