package com.zhoutao123.architect.layered.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhoutao123.architect.layered.model.exception.ErrorCode;
import com.zhoutao123.architect.layered.model.exception.NotFoundException;
import com.zhoutao123.architect.layered.untils.JsonTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

public class ExceptionInfoConfig {

  private static final Map<ErrorCode, I18NTemplate> templateMap =
      new HashMap<>(ErrorCode.values().length);

  static {
    Properties properties = new Properties();
    try {
      ClassPathResource classPathResource = new ClassPathResource("exception.properties");
      InputStream inputStream = classPathResource.getInputStream();
      BufferedReader bf =
          new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      properties.load(bf);
      Set<Object> keySet = properties.keySet();
      for (Object keyObj : keySet) {
        String key = keyObj.toString();
        String valueJson = (String) properties.get(key);
        I18NTemplate template = JsonTools.fromString(valueJson, I18NTemplate.class);

        templateMap.put(ErrorCode.valueOf(key), template);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static I18NTemplate findByErrorCode(ErrorCode errorCode) {
    return templateMap.get(errorCode);
  }

  @Data
  static class I18NTemplate {
    private String def;

    private String en;

    private String zn;

    public String getByName(String language) {
      if (!StringUtils.hasText(language)) {
        return "";
      }
      switch (language.toUpperCase()) {
        case "DEFAULT":
          return def;
        case "EN":
          return en;

        case "ZN":
          return zn;
        default:
          return "";
      }
    }
  }
}
