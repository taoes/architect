package com.zhoutao123.mq.spring.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** 测试RabbitMQ 消息类 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMessageQueue {

  @Autowired private RabbitMessagingTemplate template;

  @Test
  public void handle() {
    String routingKey = "spring-boot";
    for (int i = 0; i < 10; i++) {
      template.convertAndSend(routingKey, "hello");
    }
    System.out.println("发送消息..........OK!");
  }
}
