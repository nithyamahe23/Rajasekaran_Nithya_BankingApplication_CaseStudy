package com.bankingapplication.controller;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.Transaction;
import com.bankingapplication.model.User;
import com.bankingapplication.service.AccountService;
import com.bankingapplication.service.TransactionService;
import com.bankingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @PostMapping("/showDepositForm")
    public String showDepositForm(@RequestParam Long accountNumber, @RequestParam String accountType,
                                  @RequestParam Double balance,@RequestParam String username,  Model model)
    {
        Transaction transaction = new Transaction();

        User user = userService.getUserDetailsByUsername(username);
        transaction.setUser(user);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setUser(user);
        transaction.setAccountDetails(account);

        System.out.println(transaction.getAccountDetails());
        model.addAttribute("transaction", transaction);
        return"deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute("transaction") Transaction transaction)
    {
        String username = transaction.getUser().getEmailId();
        System.out.println("Account number"+transaction.getAccountDetails().getAccountNumber());
        transaction.setTransactionDate(java.time.LocalDate.now().toString());
        transaction.setTransactionType("Deposit");
        transactionService.saveTransaction(transaction);
        //redirectAttributes.addFlashAttribute("success", "Transaction Successfull!!!");

        return "redirect:/accounts/viewAccountDetails?username="+username;
    }

    @PostMapping("/showWithdrawalForm")
    public String showWithdrawalForm(@RequestParam Long accountNumber, @RequestParam String accountType,
                                  @RequestParam Double balance,@RequestParam String username,  Model model)
    {
        Transaction transaction = new Transaction();

        User user = userService.getUserDetailsByUsername(username);
        transaction.setUser(user);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setUser(user);
        transaction.setAccountDetails(account);

        System.out.println(transaction.getAccountDetails());
        model.addAttribute("transaction", transaction);
        return"withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute("transaction") Transaction transaction)
    {
        String username = transaction.getUser().getEmailId();
        transaction.setTransactionDate(java.time.LocalDate.now().toString());
        transaction.setTransactionType("Withdraw");
        transactionService.saveTransaction(transaction);
        //redirectAttributes.addFlashAttribute("success", "Transaction Successfull!!!");
        return "redirect:/accounts/viewAccountDetails?username="+username;
    }

    @PostMapping("/showTransactions")
    public String getTransactions(@RequestParam String username, Model model)
    {
        User user = userService.getUserDetailsByUsername(username);
        List<Transaction> transactions = transactionService.getTransactions(user);
        List<String> headers = Arrays.asList("Transaction ID", "Account Number", "Transaction Type","Transaction Date","Amount");
        model.addAttribute("headers",headers);
        model.addAttribute("transactions", transactions);
        model.addAttribute("username", username);
        return "transaction-details";
    }
}
