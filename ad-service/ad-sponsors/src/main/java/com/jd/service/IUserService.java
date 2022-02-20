package com.jd.service;

import com.jd.exception.AdException;
import com.jd.vo.CreateUserRequest;
import com.jd.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
