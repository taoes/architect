package com.zhoutao123.mq.exchange.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.zhoutao123.mq.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/** 路由模型值直连模型 */
public class DirectExchangerConsumer implements DirectMessageConfig {

  public static void main(String[] args) throws Exception {
    Channel channel = RabbitMqUtils.getChannel();
    // 创建队列
    channel.queueDeclare(queue, false, false, false, null);

    // 消费的类型
    channel.queueBind(queue, exchange, errorRoutingKey);
    channel.queueBind(queue, exchange, infoRoutingKey);
    channel.queueBind(queue, exchange, warmRoutingKey);

    Runtime.getRuntime().addShutdownHook(new Thread(RabbitMqUtils::close));

    // Receiver Message
    channel.basicConsume(
        queue,
        false,
        new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            String routingKey = envelope.getRoutingKey();
            System.out.printf("[%s]: %s", routingKey, new String(body, StandardCharsets.UTF_8));

            // 发送ACK确认
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        });
  }
}
