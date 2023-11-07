package com.brunosousa.pagamento.model;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
}
