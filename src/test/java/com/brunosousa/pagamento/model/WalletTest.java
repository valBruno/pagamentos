package com.brunosousa.pagamento.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalletTest {

    User user1;
    User user2;
    Wallet user1Wallet;
    Wallet user2Wallet;

    @BeforeEach
    void setUp() {
        user1Wallet = new Wallet(9999L, user1, 100.0 );
        user1 = new User(2L, "User Test Um", "email1@email.com", "senha qualquer", "000000000000", user1Wallet);

        user2Wallet = new Wallet(8888L, user1, 500.0 );
        user2 = new User(4L, "User Test Dois", "email2@email.com", "senha qualquer", "11111111111", user2Wallet);
    }

    @Test
    void addBalance() {

        user1.getWallet().addBalance(10.0);
        assert user1Wallet.getBalance() == 110.0;
    }

    @Test
    void subBalance() {
        user1.getWallet().subBalance(10.0);

        assert user1Wallet.getBalance() == 90.0;
    }

    @Test
    void transfer() {
        user2.getWallet().transfer(100.0, user1.getWallet());

        assert user1Wallet.getBalance() == 200.0;
        assert user2Wallet.getBalance() == 400.0;

    }
}