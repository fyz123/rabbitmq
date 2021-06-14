package com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqUtil {

    private static ConnectionFactory factory = new ConnectionFactory();

    static{
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");
    }

    public static Connection getConnection(){
        try {
            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Channel channel,Connection connection){
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
