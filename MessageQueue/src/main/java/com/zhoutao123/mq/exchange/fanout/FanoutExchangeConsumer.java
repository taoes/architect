package com.zhoutao123.mq.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.zhoutao123.mq.RabbitMqUtils;

import java.nio.charset.StandardCharsets;

/** RabbitMQ Fanout交换机模式消费者 */
public class FanoutExchangeConsumer {
  public static void main(String[] args) throws Exception {
    final Channel channel = RabbitMqUtils.getChannel();

    // 声明交换机, 其类型为 fanout
    channel.exchangeDeclare("basic-exchange", "fanout");

    // 创建四个临时队列并绑定到Fanout交换机上
    for (int i = 0; i < 4; i++) {
      new Thread(
              () -> {
                try {
                  // 创建临时队列，并将该消息队列绑定到该交换机上
                  String tempQueue = channel.queueDeclare().getQueue();
                  channel.queueBind(tempQueue, "basic-exchange", "");
                  // 准备接收消息
                  channel.basicConsume(
                      tempQueue,
                      true,
                      new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(
                            String consumerTag,
                            Envelope envelope,
                            AMQP.BasicProperties properties,
                            byte[] body) {
                          System.out.printf(
                              "队列:%s 接收到消息: %s%n",
                              tempQueue, new String(body, StandardCharsets.UTF_8));
                        }
                      });
                } catch (Exception ignore) {
                }
              })
          .start();
    }
  }
}
