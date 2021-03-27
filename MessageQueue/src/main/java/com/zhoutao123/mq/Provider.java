package com.zhoutao123.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

public class Provider {

  private static final AMQP.BasicProperties props;

  static {
    props = MessageProperties.PERSISTENT_TEXT_PLAIN;
  }

  public static void main(String[] args) throws Exception {
    Channel channel = RabbitMqUtils.getChannel();

    // 通道的绑定和具体发送并没有关系
    channel.queueDeclare("wechat", true, false, false, null);
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String content = scanner.next();
      if (content.contains("BYTE")) {
        break;
      }
      // 交换机使用的是默认交换机 amq.DEFAULT
      channel.basicPublish("", "wechat", props, content.getBytes());
      System.out.println("消息:" + content + "发送成功！");
    }
    RabbitMqUtils.close();
  }
}
