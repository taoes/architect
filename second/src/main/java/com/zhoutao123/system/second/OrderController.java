package com.zhoutao123.system.second;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

  private final RedissonClient client;

  public OrderController(RedissonClient client) {
    this.client = client;
  }

  @GetMapping
  public String createOrder(@RequestParam Long productId, @RequestParam Long userId)
      throws InterruptedException {
    RLock lock = client.getLock("PRODUCT:" + productId + ":LOCK");
    boolean getLock = lock.tryLock(10, TimeUnit.SECONDS);
    if (!getLock) {
      throw new RuntimeException("系统繁忙，请稍后重试");
    }
    try {
      RAtomicLong atomicLong = client.getAtomicLong("PRODUCT:COUNT");
      Long count = atomicLong.get();
      if (count <= 0) {
        log.info("购买失败,{} 卖光了", userId);
        return "卖光了";
      }
      RSet<Long> sale = client.getSet("PRODUCT:SALE");
      if (sale.contains(userId)) {
        log.info("购买失败,{}不可重复购买", userId);
        return "不可重复购买";
      }
      long cpint = atomicLong.decrementAndGet();
      sale.add(userId);
      log.info("购买成功,{}，剩余:{}", userId, cpint);
      return "OK";
    } finally {
      lock.unlock();
    }
  }

  @GetMapping("/init")
  public String ok(Integer count) {
    client.getAtomicLong("PRODUCT:COUNT").getAndSet(count);
    client.getBucket("PRODUCT:SALE").delete();
    return "OK";
  }

  @GetMapping("/sale")
  public Set<Long> getSaleUser() {
    RSet<Long> user = client.getSet("PRODUCT:SALE");
    return user.readAll();
  }
}
