package com.brunosousa.pagamento.controller;

import com.brunosousa.pagamento.model.User;
import com.brunosousa.pagamento.model.Wallet;
import com.brunosousa.pagamento.model.form.TransferForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    User user1;
    Wallet user1Wallet;

    @BeforeEach
    void setUp() {
        user1Wallet = new Wallet(9999L, user1, 100.0 );
        user1 = new User(2L, "User Test Um", "email1@email.com", "senha qualquer", "000000000000", user1Wallet);
    }

    @Test
    void getUser() throws Exception {

        long userid = 1L;
        User user = new User();
        user.setId(userid);
        user.setName("José da Silva");
        user.setEmail("jose.silva@email.com");
        user.setPassword("hash_qualquer");
        user.setCpf("12345678910");

//        TODO teste integrado, fazer o mock do repository
        this.mockMvc.perform(get("/api/v1/users/{UserId}", userid))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    void getUserBalance() throws Exception {

//        TODO teste integrado, fazer o mock do repository
        this.mockMvc.perform(get("/api/v1/users/{UserId}/balance", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("500.0"));
    }

    @Test
    void getTransfer() throws Exception {

        TransferForm transferForm = new TransferForm(2L, 50.0);

        this.mockMvc.perform(post("/api/v1/users/{UserId}/transfer", 1)
                        .content(objectMapper.writeValueAsString(transferForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer Success!"));
    }
}