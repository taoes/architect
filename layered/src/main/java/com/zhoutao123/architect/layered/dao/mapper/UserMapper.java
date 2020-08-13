package com.zhoutao123.architect.layered.dao.mapper;

import com.zhoutao123.architect.layered.dao.UserDO;
import java.util.UUID;
import org.springframework.stereotype.Component;

// Mock MyBatis Mapper
@Component
public class UserMapper {

  public UserDO findByUsername(String username) {
    return new UserDO(UUID.randomUUID().toString(), username, username.toUpperCase());
  }

}
