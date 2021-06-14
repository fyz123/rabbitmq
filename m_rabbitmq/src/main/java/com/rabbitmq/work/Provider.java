package com.rabbitmq.work;

import com.rabbitmq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;

/**
 * work模型
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);

        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", null,
                    ("第 " + i + "条数据:" + UUID.randomUUID().toString()).getBytes());
        }

        RabbitmqUtil.closeConnection(channel,connection);
    }
}


