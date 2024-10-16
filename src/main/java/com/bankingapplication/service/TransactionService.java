package com.bankingapplication.service;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.Transaction;
import com.bankingapplication.model.User;
import com.bankingapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public void saveTransaction(Transaction transaction)
    {
        //Update transaction table
        transactionRepository.save(transaction);

        //Update balance in accounts table
        Account account = transaction.getAccountDetails();
        //If transaction is deposit
        if(transaction.getTransactionType().equals("Deposit"))
        {

            double updatedBalance = account.getBalance() + transaction.getTransactionAmount();
            BigDecimal bigDecimal = new BigDecimal(updatedBalance).setScale(2, RoundingMode.HALF_UP);
            account.setBalance(bigDecimal.doubleValue());
        }else if(transaction.getTransactionType().equals("Withdraw"))
        {
            double updatedBalance = account.getBalance() - transaction.getTransactionAmount();
            account.setBalance(updatedBalance);BigDecimal bigDecimal = new BigDecimal(updatedBalance).setScale(2, RoundingMode.HALF_UP);
            account.setBalance(bigDecimal.doubleValue());
        }
        accountService.updateAccount(account.getAccountNumber(), account);


    }

    //To get transactions associated with user
    public List<Transaction> getTransactions(User user)
    {
        List<Transaction> transactions = transactionRepository.findByUser(user).get();
        return transactions;
    }
    //To delete Transaction
    public void deleteTransaction(Transaction transaction)
    {
        transactionRepository.delete(transaction);
    }
}
