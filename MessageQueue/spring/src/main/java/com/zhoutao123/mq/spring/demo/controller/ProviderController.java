package com.zhoutao123.mq.spring.demo.controller;

import com.zhoutao123.mq.spring.demo.model.DirectMessage;
import com.zhoutao123.mq.spring.demo.model.QueueMessage;
import com.zhoutao123.mq.spring.demo.model.TopicMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 生产者Controller */
@Slf4j
@RestController
@RequestMapping("/mq/provider")
public class ProviderController {

  private final RabbitMessagingTemplate template;

  public ProviderController(RabbitMessagingTemplate template) {
    this.template = template;
  }

  /** 发送消息直接到队列中 */
  @GetMapping("/queue")
  public String sendMessageToQueue() {
    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String currentDatetime = LocalDateTime.now().format(pattern);
    for (int i = 0; i < 100; i++) {
      template.convertAndSend("queue-message", new QueueMessage(i, currentDatetime));
    }
    return "OK";
  }

  /** 发送消息到Fanout交换机中 */
  @GetMapping("/fanout")
  public String sendMessageToFanoutExchange(@RequestParam(defaultValue = "20") int count) {
    for (int i = 0; i < count; i++) {
      String payload1 = "这是一个info消息";
      DirectMessage message1 = new DirectMessage(i, payload1);
      template.convertAndSend("logs2", "info", message1);

      String payload2 = "这是一个error消息";
      DirectMessage message2 = new DirectMessage(i, payload2);
      template.convertAndSend("logs2", "error", message2);
    }
    log.info("发送Direct消息完成，发送数量:{}", count);
    return "OK";
  }

  /** 发送消息到 topic 交换机中 */
  @GetMapping("/topic")
  public String sendMessageToTopicExchange(@RequestParam(defaultValue = "20") int count) {
    for (int i = 0; i < count; i++) {
      String payload1 = "用户" + i + "被创建!";
      TopicMessage message1 = new TopicMessage(i, payload1);
      template.convertAndSend("topic-user", "user.create", message1);
    }
    log.info("发送Direct消息完成，发送数量:{}", count);
    return "OK";
  }
}
