package com.bankingapplication.service;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.Transaction;
import com.bankingapplication.model.User;
import com.bankingapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    //Register New User
    public void registerUser(User user)
    {
        userRepository.save(user);
    }


    //Check whether username exists in db
    public User checkUserExists(String username)
    {
        Optional<User> userOptional = userRepository.findByEmailId(username);
        if(userOptional.isPresent())
        {
            return userOptional.get();
        }else{
            return null;
        }
    }

    public User getUserDetailsByUsername(String username)
    {
        Optional<User> userOptional = userRepository.findByEmailId(username);
        return userOptional.get();
    }

    //Update User Details
    //To update account balance when a transaction is made
    public void updateUser(String username, User updatedUser) {
        userRepository.findByEmailId(username).map(
                user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setGender(updatedUser.getGender());
                    user.setAddress(updatedUser.getAddress());
                    user.setContactNumber(updatedUser.getContactNumber());
                    return userRepository.save(user);
                });
    }

    public void deleteUser(User user){
        //Delete transactions associated with user
        List<Transaction> transactions = user.getTransactions();
        for(Transaction transaction : transactions){
            transactionService.deleteTransaction(transaction);
        }

        //Delete Accounts associated with user
        List<Account> accounts = user.getAccounts();
        for(Account account : accounts){
            accountService.deleteAccount(account);
        }
        //Delete User
        userRepository.delete(user);
    }
}
