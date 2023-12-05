package com.example.backendchallengepicpay.picpaychallenge.service;

import com.example.backendchallengepicpay.picpaychallenge.domain.transaction.Transaction;
import com.example.backendchallengepicpay.picpaychallenge.domain.user.User;
import com.example.backendchallengepicpay.picpaychallenge.domain.user.UserType;
import com.example.backendchallengepicpay.picpaychallenge.dto.TransactionDTO;
import com.example.backendchallengepicpay.picpaychallenge.repositories.TransactionRepository;
import com.example.backendchallengepicpay.picpaychallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionService{
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());


        Transaction newTransaction= new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!");


        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a890-496f-8c9a-3442cf30dae6", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");

            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }





}
