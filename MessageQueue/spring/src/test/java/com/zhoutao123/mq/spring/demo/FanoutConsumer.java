package com.zhoutao123.mq.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/** Fanout 消息接收模式 */
@Slf4j
@Component
public class FanoutConsumer {

  @RabbitListener(queuesToDeclare = @Queue())
  public void consumer1(String message) {
    log.info("接收到消息1:{}", message);
  }

  @RabbitListener(queuesToDeclare = @Queue(value = "spring-boot"))
  public void consumer2(String message) {
    log.info("接收到消息2:{}", message);
  }
}
