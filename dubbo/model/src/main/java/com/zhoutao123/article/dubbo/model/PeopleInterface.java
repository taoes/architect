package com.zhoutao123.article.dubbo.model;

import java.util.List;

public interface PeopleInterface {
    /**
     * 查询全部的用户
     */
    List<People> findAll(String name, int count);

    /**
     * 检查用户是否存在
     */
    Boolean exist(String name);
}
