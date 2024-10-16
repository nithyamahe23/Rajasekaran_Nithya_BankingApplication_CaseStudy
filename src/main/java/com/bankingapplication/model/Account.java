package com.bankingapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_type")
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private User user;

    @OneToMany(mappedBy = "accountDetails")
    private List<Transaction> transactions;
}
