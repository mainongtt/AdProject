package com.jd.controller;

import com.alibaba.fastjson.JSON;
import com.jd.exception.AdException;
import com.jd.service.IUserService;
import com.jd.vo.CreateUserRequest;
import com.jd.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserOPController {

    private final IUserService userService;

    @Autowired(required = false)
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(
            @RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}",
                JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
