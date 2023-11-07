package com.brunosousa.pagamento.service;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long UserId) {
        return userRepository.findById(UserId).orElseThrow();
    }
}
