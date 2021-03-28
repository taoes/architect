package com.zhoutao123.mq.spring.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Topic 消息模型实体
 *
 * @apiNote 需要实现 {@link Serializable} 接口
 */
@Data
public class TopicMessage implements Serializable {

  private int uid;

  private String content;

  public TopicMessage(int uid, String content) {
    this.content = content;
    this.uid = uid;
  }
}
