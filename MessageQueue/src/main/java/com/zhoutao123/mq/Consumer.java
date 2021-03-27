package com.zhoutao123.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/** 消息的消费 */
public class Consumer {

  public static void main(String[] args) throws IOException {
    Channel channel = RabbitMqUtils.getChannel();
    channel.queueDeclare("wechat", true, false, false, null);
    // 每次只接受一个消息
    channel.basicQos(1);
    channel.basicConsume(
        "wechat",
        false,
        new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            // body队列中取出的消息
            System.out.println("接收到消息:" + new String(body, StandardCharsets.UTF_8));

            // 手动确认消息
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        });
  }
}
