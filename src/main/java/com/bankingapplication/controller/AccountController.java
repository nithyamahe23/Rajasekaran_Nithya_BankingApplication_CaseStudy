package com.bankingapplication.controller;

import com.bankingapplication.model.Account;
import com.bankingapplication.model.Transaction;
import com.bankingapplication.model.User;
import com.bankingapplication.service.AccountService;
import com.bankingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/showAddAccountForm")
    public String showAddAccountForm1(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("account", new Account());
        return "add-account";
    }

    @PostMapping("/showAddAccountForm")
    public String showAddAccountForm(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("account", new Account());
        return "add-account";
    }

    @PostMapping("/addAccount/{username}")
    public String addAccount(@ModelAttribute("account") Account account,
                             @PathVariable String username
                             )
    {
        double initialDeposit = 200;
        User user = userService.getUserDetailsByUsername(username);
        account.setUser(user);
        account.setBalance(initialDeposit);
        accountService.createAccount(account);
        return "redirect:/accounts/viewAccountDetails?username=" + username;
    }
    @GetMapping("/viewAccountDetails")
    public String viewAccountDetails(@RequestParam("username") String username, Model model) {
        User user = userService.getUserDetailsByUsername(username);
        List<Account> accounts = user.getAccounts();
        List<String> headers = Arrays.asList("Account Number", "Account Type", "Balance");
        System.out.println(accounts);
        model.addAttribute("headers", headers);
        model.addAttribute("accounts", accounts);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("username", username);
        return "account-details";
    }
}
