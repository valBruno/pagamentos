package com.brunosousa.pagamento.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WALLETS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

//    TODO uma por usuário/cpf? bilateral?
    @OneToOne(mappedBy = "wallet")
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private User user;


    private Double balance;

    public void addBalance(Double value) {
        this.balance += value;
    }

    public void subBalance(Double value) {
        this.balance -= value;
    }

    public void transfer(Double value, Wallet wallet) {
        this.subBalance(value);
        wallet.addBalance(value);
    }

}
