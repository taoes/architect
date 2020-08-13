package com.zhoutao123.architect.layered.service.impl;

import com.zhoutao123.architect.layered.dao.UserDO;
import com.zhoutao123.architect.layered.dao.mapper.UserMapper;
import com.zhoutao123.architect.layered.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public UserDO login(String username, String password) {
    return userMapper.findByUsername(username);
  }
}
