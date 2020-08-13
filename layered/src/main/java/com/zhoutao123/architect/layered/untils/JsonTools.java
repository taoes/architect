package com.zhoutao123.architect.layered.untils;

import com.alibaba.fastjson.JSON;

public class JsonTools {
  public static <T> T fromString(String json, Class<T> clazz) {

    try {
      return JSON.parseObject(json, clazz);
    } catch (Exception e) {
      return null;
    }
  }
}
