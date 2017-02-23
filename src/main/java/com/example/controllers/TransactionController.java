package com.example.controllers;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;
import com.example.model.TransactionListingDTO;
import com.example.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dani on 2017-02-09.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id, @RequestParam String username, @RequestParam String password){
        TransactionDTO transactionDTO = transactionService.getTransaction(id,username,password);

        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionIdentifierDTO incomingTransaction){
        TransactionDTO transactionDTO = transactionService.addTransaction(incomingTransaction);

        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    // not woorking yet!
    @RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
    public ResponseEntity<TransactionListingDTO> getTransactions(@PathVariable Long userid , @RequestParam Long startPosition, @RequestParam Long endPosition, @RequestParam String username, @RequestParam String password) {
        TransactionListingDTO transactionListingDTO;
        transactionListingDTO= transactionService.getTransactions(userid,startPosition,endPosition,username,password);

        if(transactionListingDTO != null){
            return new ResponseEntity<>(transactionListingDTO, HttpStatus.OK);
        }
        logger.info("RETURNING BAD REQUEST FOR SOME REASON!");
        return new ResponseEntity<>(new TransactionListingDTO(), HttpStatus.BAD_REQUEST);
    }
}
