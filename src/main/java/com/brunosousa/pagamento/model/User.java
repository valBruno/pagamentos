package com.brunosousa.pagamento.model;

import com.brunosousa.pagamento.model.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

/**
 * User
 * Para ambos tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha.
 * CPF/CNPJ e e-mails devem ser únicos no sistema.
 * Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail.
 */
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = "uk_email_user", columnNames = {"EMAIL"}),
        @UniqueConstraint(name = "ok_cpf_user", columnNames = {"CPF"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    //TODO lengths, type etc
    @Column(nullable = false, length = 255, name = "NAME")
    private String name;

    @Column(nullable = false, length = 255, name = "EMAIL")
    private String email;

    @Column(nullable = false, length = 255, name = "PASSWORD")
    private String password;

    @Column(nullable = false, length = 255, name = "CPF")
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WALLET_ID", referencedColumnName = "id")
    private Wallet wallet;

    public UserDTO toDto() {
        return new UserDTO(this.name, this.email, this.password, this.cpf);
    }
}
