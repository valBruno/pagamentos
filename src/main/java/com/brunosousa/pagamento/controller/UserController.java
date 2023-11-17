package com.brunosousa.pagamento.controller;

import com.brunosousa.pagamento.model.dto.UserDTO;
import com.brunosousa.pagamento.model.form.TransferForm;
import com.brunosousa.pagamento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@SuppressWarnings("unused")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{UserId}")
    @SuppressWarnings("unused")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long UserId) {
        return ResponseEntity.ok(userService.getUserById(UserId));
    }
    @GetMapping("/{UserId}/balance")
    @SuppressWarnings("unused")
    public ResponseEntity<String> getUserBalance(@PathVariable Long UserId) {

        return ResponseEntity.ok(userService.getBalance(UserId));
    }

    @PostMapping("/{UserId}/transfer")
    @SuppressWarnings("unused")
    public ResponseEntity<String> getTransfer(@PathVariable Long UserId,@RequestBody TransferForm form) {

        return ResponseEntity.ok(userService.transfer(UserId, form));
    }


}
