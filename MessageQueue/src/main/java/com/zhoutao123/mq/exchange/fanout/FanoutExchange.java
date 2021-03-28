package com.zhoutao123.mq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.zhoutao123.mq.RabbitMqUtils;

/** RabbitMQ 交换机模式 */
public class FanoutExchange {
  public static void main(String[] args) throws Exception {
    Channel channel = RabbitMqUtils.getChannel();

    // 声明交换机, 其类型为 fanout，并且发送消息到该交换机中
    channel.exchangeDeclare("basic-exchange", "fanout");
    channel.basicPublish("basic-exchange", "", null, "这是经过Fanout交换机的消💂".getBytes());

    // 关闭连接
    RabbitMqUtils.close();
  }
}
