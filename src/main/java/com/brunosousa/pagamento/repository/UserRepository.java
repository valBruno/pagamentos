package com.brunosousa.pagamento.repository;

import com.brunosousa.pagamento.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    public Optional<User> findById(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setName("Mock User");
        user.setEmail("mock@email.com");
        user.setPassword("passwordhash");
        user.setCpf("12345678910");

        return Optional.of(user);
    }
}
