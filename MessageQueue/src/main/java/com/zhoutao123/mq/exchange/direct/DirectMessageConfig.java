package com.zhoutao123.mq.exchange.direct;

public interface DirectMessageConfig {

  String exchange = "logs";

  String queue = "logs-queue";

  String errorRoutingKey = "error";

  String infoRoutingKey = "info";

  String warmRoutingKey = "warm";
}
