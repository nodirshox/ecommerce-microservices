package com.ecommerce.auth.service.service;

import com.ecommerce.auth.service.model.LoginRequest;
import com.ecommerce.auth.service.model.LoginResponse;
import com.ecommerce.auth.service.model.Response;
import com.ecommerce.auth.service.model.TokenRequest;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);

    Response validateToken(TokenRequest tokenRequest);
}