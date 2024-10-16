package com.bankingapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class  Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account accountDetails;
}
