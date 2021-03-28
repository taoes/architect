package com.zhoutao123.mq.exchange.topic;

public interface TopicMessageConfig {

  String exchange = "logs-topic";

  String queue = "logs-queue";

  String errorRoutingKey = "error";

  String infoRoutingKey = "info.#";

  String warmRoutingKey = "warm";
}
