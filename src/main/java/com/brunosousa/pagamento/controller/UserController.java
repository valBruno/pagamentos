package com.brunosousa.pagamento.controller;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@SuppressWarnings("unused")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{UserId}")
    @SuppressWarnings("unused")
    public ResponseEntity<User> getUserById(@PathVariable Long UserId) {
        return ResponseEntity.ok(userService.getUserById(UserId));
    }
}
