package com.zhoutao123.architect.layered.dao;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDO {

  //  用户ID
  private String id;

  // 用户名
  private String username;

  // 密码
  private String password;

  // 创建时间
  private LocalDateTime createTime;

  // 更新时间
  private LocalDateTime updateTime;

  public UserDO(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }
}
