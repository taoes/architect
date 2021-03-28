package com.zhoutao123.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqUtils {

  private static Channel channel;
  private static Connection connection;
  private static boolean closed = true;

  public static Channel getChannel() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setPort(5672);
    factory.setVirtualHost("/dev");
    factory.setUsername("admin");
    factory.setPassword("123456");

    try {
      connection = factory.newConnection();
      channel = connection.createChannel();
      closed = false;
      return channel;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void close() {
    if (closed) {
      return;
    }

    System.out.println("关闭MQ连接");

    try {
      if (channel != null && channel.isOpen()) {
        channel.close();
      }

      if (connection != null && connection.isOpen()) {
        connection.close();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
