package com.bankingapplication.service;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.User;
import com.bankingapplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    //To add New Account
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    //To get Account details associated with user
    public List<Account> getAccountDetails(User user){
        Optional <List<Account>> accounts = accountRepository.findByUser(user);
        return accounts.orElse(null);
    }

    //To update account balance when a transaction is made
    public void updateAccount(Long accountNumber, Account updatedAccount) {
        accountRepository.findById(accountNumber).map(
                account -> {
                    account.setBalance(updatedAccount.getBalance());
                    return accountRepository.save(account);
                });
    }

    //To delete account
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
}
