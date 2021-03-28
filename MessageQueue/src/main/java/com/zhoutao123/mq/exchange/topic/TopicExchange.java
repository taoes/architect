package com.zhoutao123.mq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.zhoutao123.mq.RabbitMqUtils;

import java.io.IOException;

public class TopicExchange implements TopicMessageConfig {

  public static void main(String[] args) throws IOException {
    Channel channel = RabbitMqUtils.getChannel();

    // 声明交换机
    channel.exchangeDeclare(exchange, "topic");

    // 发布消息
    channel.basicPublish(exchange, "info.user", null, "你好,这是来自topic的消息".getBytes());
    channel.basicPublish(exchange, "info.user.name", null, "你好,这是来自topic的消息".getBytes());
    RabbitMqUtils.close();
  }
}
