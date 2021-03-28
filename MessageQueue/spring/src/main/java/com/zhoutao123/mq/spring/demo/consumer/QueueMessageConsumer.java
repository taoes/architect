package com.zhoutao123.mq.spring.demo.consumer;

import com.zhoutao123.mq.spring.demo.model.DirectMessage;
import com.zhoutao123.mq.spring.demo.model.QueueMessage;
import com.zhoutao123.mq.spring.demo.model.TopicMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/** 队列消息监控程序 */
@Slf4j
@Component
public class QueueMessageConsumer {

  /** 从消息队列 queue-message中接收消息 */
  @RabbitListener(queuesToDeclare = @Queue("queue-message"))
  public void handQueueMessage(QueueMessage message) throws InterruptedException {
    log.info("接收到消息ID:{}", message.getUid());
    log.info("消息内容:{}", message.toString());
    TimeUnit.SECONDS.sleep(2);
    log.info("消息:{} 处理完成", message.getUid());
  }

  /** 从和某个交换机绑定的队列上获取消息 */
  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue("handleInfoAndError"),
            exchange = @Exchange(value = "logs2"),
            key = {"info", "error"})
      })
  public void handDirectMessage(DirectMessage message) throws InterruptedException {
    log.info("infoAndError:{}", message.getUid());
    log.info("infoAndError:{}", message.toString());
    TimeUnit.SECONDS.sleep(2);
    log.info("infoAndError:{} 处理完成", message.getUid());
  }

  /** 从和某个交换机绑定的队列上获取消息 */
  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue("handleInfo"),
            exchange = @Exchange(value = "logs2"),
            key = {"info"})
      })
  public void handDirectMessage2(DirectMessage message) throws InterruptedException {
    log.info("info:{}", message.getUid());
    log.info("info:{}", message.toString());
    TimeUnit.SECONDS.sleep(1);
    log.info("info:{} 处理完成", message.getUid());
  }

  /** 从和某个交换机绑定的队列上获取消息 */
  @RabbitListener(
      bindings = {
        @QueueBinding(
            value = @Queue("handUser"),
            exchange = @Exchange(value = "topic-user", type = "topic"),
            key = {"user.create"})
      })
  public void handTopicMessage1(TopicMessage message) throws InterruptedException {
    log.info("接收到用户被创建的主题消息:{}", message.getUid());
    TimeUnit.SECONDS.sleep(1);
    log.info("消息{}处理完成", message.getUid());
  }
}
