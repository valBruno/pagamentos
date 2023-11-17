package com.brunosousa.pagamento.service;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.model.dto.UserDTO;
import com.brunosousa.pagamento.model.form.TransferForm;
import com.brunosousa.pagamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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

    @Transactional
    public String transfer(Long userId, TransferForm form) {

        User payer = userRepository.findByIdAndFetchWalletsEagerly(userId).orElseThrow();

        User payee = userRepository.findByIdAndFetchWalletsEagerly(form.getPayeeId()).orElseThrow();

        if (payer.getWallet().getBalance() >= form.getValue()) {
            payer.getWallet().transfer(form.getValue(), payee.getWallet());

            userRepository.saveAllAndFlush(Arrays.asList(payer, payee));

            return "Transfer Success!";
        }

        return "Transfer Fail!";
    }
}
