package com.zhoutao123.mq.exchange.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.zhoutao123.mq.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TopicExchangeConsumer implements TopicMessageConfig {

  public static void main(String[] args) throws IOException {
    Channel channel = RabbitMqUtils.getChannel();

    // 声明交换机
    channel.exchangeDeclare(exchange, "topic");

    // 创建临时队列
    String queue = channel.queueDeclare().getQueue();

    // 绑定队列和交换机
    channel.queueBind(queue, exchange, infoRoutingKey);

    // 发布消息
    channel.basicConsume(
        queue,
        false,
        new DefaultConsumer(channel) {

          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            System.out.printf(
                "接收到消息:%s-%s\n",
                envelope.getRoutingKey(), new java.lang.String(body, StandardCharsets.UTF_8));
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        });
  }
}
