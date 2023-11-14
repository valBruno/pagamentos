package com.brunosousa.pagamento.service;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.model.dto.UserDTO;
import com.brunosousa.pagamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO getUserById(Long UserId) {
        User user = userRepository.findById(UserId).orElseThrow();

        return user.toDto();
    }

    public String getBalance(Long userId) {
        User user = userRepository.findByIdAndFetchWalletsEagerly(userId).orElseThrow();

        System.out.println(user);

        return user.getWallet().getBalance().toString();
    }
}
