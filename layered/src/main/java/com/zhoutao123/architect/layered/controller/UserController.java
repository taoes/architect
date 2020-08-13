package com.zhoutao123.architect.layered.controller;

import com.zhoutao123.architect.layered.dao.UserDO;
import com.zhoutao123.architect.layered.model.Response;
import com.zhoutao123.architect.layered.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public Response<UserDO> login(String username, String password) {
    UserDO login = userService.login(username, password);
    return Response.ok(login);
  }
}
