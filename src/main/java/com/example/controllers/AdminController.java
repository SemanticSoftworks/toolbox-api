package com.example.controllers;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;
import com.example.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dani on 2017-02-10.
 */
@RestController
@RequestMapping("/admin")
public class AdminController{
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;


    // admin features
    @RequestMapping(value="/transaction" , method = RequestMethod.PUT, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionIdentifierDTO incomingTransaction){
        TransactionDTO transactionDTO = adminService.updateTransaction(incomingTransaction);

        if(transactionDTO != null){
            transactionDTO.setTransactionId(incomingTransaction.getTransactionId());
            transactionDTO.setDescription(incomingTransaction.getDescription());
            transactionDTO.setDate(incomingTransaction.getDate() != null? incomingTransaction.getDate() : null);
            transactionDTO.setSum(incomingTransaction.getSum());
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/transaction/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<TransactionDTO> deleteTransaction(@PathVariable Long id,@RequestParam String username, @RequestParam String password){
        TransactionDTO transactionDTO = adminService.deleteTransactionById(id, username, password);
        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }
}
