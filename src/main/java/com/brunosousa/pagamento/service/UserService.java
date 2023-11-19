package com.brunosousa.pagamento.service;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.model.dto.UserDTO;
import com.brunosousa.pagamento.model.form.TransferForm;
import com.brunosousa.pagamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${authorization.url}")
    private String authorizationURL;

    @Value("${authorization.timeoutUrl}")
    private String notificationURL;

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

            if (!getTransferAuthorization()) {
                throw new RuntimeException("Transfer Fail!");
            }

            userRepository.saveAllAndFlush(Arrays.asList(payer, payee));

            try {
                CompletableFuture<String> notification = sendNotification(payee.getEmail());
                System.out.println(notification.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
//              TODO: notification queue?
            }

            return "Transfer Success!";
        }

        return "Transfer Fail!";
    }

    public Boolean getTransferAuthorization() {

        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                return restTemplate.getForObject(authorizationURL, Boolean.class);
            } catch (HttpServerErrorException e) {
                if (++count == maxTries) {
                    System.out.println("Error on get transfer authorization, max attempts reached");
                    return false;
                }
                System.out.println("Error on get transfer authorization, attempt " + count + ", retrying...");
            }
        }
    }

    @Async
    public CompletableFuture<String> sendNotification(String userEmail) {

        int count = 0;
        int maxTries = 3;
//        TODO: notification service
        while (true) {
            try {
                String results = restTemplate.getForObject(notificationURL, String.class);
                return CompletableFuture.completedFuture(results);
            } catch (HttpServerErrorException e) {
                if (++count == maxTries) {
                    System.out.println("Error on send notification to "+userEmail+", max attempts reached");
                    throw e;
                }
                System.out.println("Error on send notification to "+userEmail+", attempt " + count + ", retrying...");
            }
        }
    }
}
