package com.rabbitmq.fanout;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();

        //通道声明交换机
        channel.exchangeDeclare("logs","fanout");

        channel.basicPublish("logs","",null,"fanout msg".getBytes());
        RabbitmqUtil.closeConnection(channel,connection);
    }
}
