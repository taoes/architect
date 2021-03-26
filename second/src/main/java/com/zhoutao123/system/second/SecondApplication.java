package com.zhoutao123.system.second;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecondApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecondApplication.class, args);
  }

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(2);
    return Redisson.create(config);
  }
}
