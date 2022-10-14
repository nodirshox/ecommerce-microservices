package com.ecommerce.auth.service.controller;

import com.ecommerce.auth.service.model.LoginRequest;
import com.ecommerce.auth.service.model.Response;
import com.ecommerce.auth.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Response response = new Response(userService.login(loginRequest), true);
        return ResponseEntity.ok().body(response);
    }
}
