package com.zhoutao123.architect.layered.service;

import com.zhoutao123.architect.layered.dao.UserDO;

public interface UserService {


  /**
   * 返回用户的ID
   * @return
   */
  UserDO login(String username, String password);


}
