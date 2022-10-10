package com.ecommerce.auth.service.service;

import com.ecommerce.auth.service.model.LoginRequest;
import com.ecommerce.auth.service.model.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}