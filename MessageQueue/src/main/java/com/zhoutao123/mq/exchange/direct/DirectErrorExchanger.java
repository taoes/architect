package com.zhoutao123.mq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.zhoutao123.mq.RabbitMqUtils;

/** 路由模型值直连模型 */
public class DirectErrorExchanger implements DirectMessageConfig {

  public static void main(String[] args) throws Exception {
    Channel channel = RabbitMqUtils.getChannel();
    // 创建队列
    channel.queueDeclare(queue, false, false, false, null);

    // 声明交换机
    channel.exchangeDeclare(exchange, "direct");

    // 发送消息
    channel.basicPublish(exchange, errorRoutingKey, null, "你好".getBytes());

    RabbitMqUtils.close();
  }
}
