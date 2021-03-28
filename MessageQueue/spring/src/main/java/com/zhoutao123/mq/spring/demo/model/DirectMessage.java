package com.zhoutao123.mq.spring.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息模型实体
 *
 * @apiNote 需要实现 {@link Serializable} 接口
 */
@Data
public class DirectMessage implements Serializable {

  private int uid;

  private String content;

  public DirectMessage(int uid, String content) {
    this.content = content;
    this.uid = uid;
  }
}
