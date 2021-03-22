package com.zhoutao123.article.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhoutao123.article.dubbo.exception.CustomerDubboException;
import com.zhoutao123.article.dubbo.model.People;
import com.zhoutao123.article.dubbo.model.PeopleInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service(group = "USER", cache = "lru")
public class PeopleService implements PeopleInterface {
    @Override
    public List<People> findAll(String name, int count) {
        List<People> people = new ArrayList<>(10);
        for (int i = 0; i < count; i++) {
            people.add(new People(name, i));
        }
        System.out.println("数据生成完成....");
        return people;
    }

    @Override
    public Boolean exist(String name) {
        if (Objects.equals(name, "123")) {
            throw new CustomerDubboException("自定义的Dubbo异常");
        }
        return name != null && name.contains("abc");
    }
}
