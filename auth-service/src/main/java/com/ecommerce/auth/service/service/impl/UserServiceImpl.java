package com.ecommerce.auth.service.service.impl;

import com.ecommerce.auth.service.entity.User;
import com.ecommerce.auth.service.model.LoginRequest;
import com.ecommerce.auth.service.model.LoginResponse;
import com.ecommerce.auth.service.model.Response;
import com.ecommerce.auth.service.model.TokenRequest;
import com.ecommerce.auth.service.repository.UserRepository;
import com.ecommerce.auth.service.security.JwtHelper;
import com.ecommerce.auth.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        }
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Credentials");
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken);
        return loginResponse;
    }

    @Override
    public Response validateToken(TokenRequest tokenRequest) {
        boolean isValid = jwtHelper.validateToken(tokenRequest.getToken());
        if (!isValid) {
            return new Response(null,false);
        }
        return new Response(null,true);
    }
}