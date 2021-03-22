package com.zhoutao123.article.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhoutao123.article.dubbo.model.People;
import com.zhoutao123.article.dubbo.model.PeopleInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PersonController {

    /**
     * <ul>
     * <li>配置重试次数: retries</>
     * <li>配置负载均衡: load balance {@link com.alibaba.dubbo.rpc.cluster.LoadBalance} </>
     * <li>配置超时时间: timeout</>
     * </>
     */
    @Reference(group = "USER")
    private PeopleInterface peopleInterface;


    @GetMapping("/user")
    private List<People> find(int count) {
        return peopleInterface.findAll("张三", count);
    }


    @GetMapping("/user/exists")
    private Boolean find(@RequestParam String name) {
        return peopleInterface.exist(name);
    }
}
