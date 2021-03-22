package com.zhoutao123.java.se.collect.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 高并发秒杀程序
 */
public class MiaoSha {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost", 6379);
        //  如果Redis存在KEY,那么则进行删除操作，否则则创建
        String lua =
                "if redis.call('get',KEYS[1])  == false then\n" +
                        "    redis.call('set', KEYS[1], ARGV[1])\n" +
                        "    return '更新了KEY'\n" +
                        "else\n" +
                        "    redis.call('del', KEYS[1])\n" +
                        "    return '删除了KEY'\n" +
                        "end";

        Object eval = jedis.eval(lua, Collections.singletonList("name"), Collections.singletonList("zhangsan"));
        System.out.println(eval);

        //language=Lua
        lua = "if redis.call('get', KEYS[1]) == false then\n" +
                "    redis.call('set', KEYS[1], 0)\n" +
                "    return 0\n" +
                "else\n" +
                "    return redis.call('incr', KEYS[1])\n" +
                "end";

        List<String> age1 = Arrays.asList("age");
        List<String> argv = Collections.emptyList();

        Long start = System.currentTimeMillis();
        jedis.del("age");
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipelined.eval(lua, age1, argv);
        }
        pipelined.sync();
        Object age = jedis.get("age");
        System.out.println(age);
        System.out.println(System.currentTimeMillis() - start + "ms");

        start = System.currentTimeMillis();
        jedis.del("age");
        for (int i = 0; i < 1000; i++) {
            jedis.eval(lua, age1, argv);
        }
        age = jedis.get("age");
        System.out.println(age);
        System.out.println(System.currentTimeMillis() - start + "ms");


        // 实现分布式锁
        //language=Lua
        lua = "if redis.call('exists', KEYS[1]) == 0 then\n" +
                "    redis.call('hincrby', KEYS[1], ARGV[2], 1)\n" +
                "    redis.call('pexpire', KEYS[1], ARGV[1])\n" +
                "    return nil\n" +
                "end\n" +
                "\n" +
                "if redis.call('hexists', KEYS[1], ARGV[2]) == 1 then\n" +
                "    redis.call('hincrby', KEYS[1], ARGV[2])\n" +
                "    redis.call('pexpire', KEYS[1], ARGV[1])\n" +
                "    return nil\n" +
                "end\n" +
                "\n" +
                "return redis.call('pttl', KEYS[1])\n";


    }


}
