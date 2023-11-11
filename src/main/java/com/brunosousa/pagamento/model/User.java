package com.brunosousa.pagamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 * Para ambos tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha.
 * CPF/CNPJ e e-mails devem ser únicos no sistema.
 * Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail.
 */
@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//TODO lengths, type etc
    @Column(nullable = false, length = 255, name = "NAME")
    private String name;

    @Column(nullable = false, length = 255, name = "EMAIL", unique = true)
    private String email;

    @Column(nullable = false, length = 255, name = "PASSWORD")
    private String password;

    @Column(nullable = false, length = 255, name = "CPF", unique = true)
    private String cpf;
}
