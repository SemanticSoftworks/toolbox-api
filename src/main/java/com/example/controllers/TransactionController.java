package com.example.controllers;

import com.example.model.TransactionDTO;
import com.example.model.TransactionListingDTO;
import com.example.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    // ÄNDRAT:
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TransactionDTO transactionDTO = transactionService.getTransaction(id);

        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    // ÄNDRAT:
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO incomingTransaction){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TransactionDTO transactionDTO = transactionService.addTransaction(incomingTransaction);

        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    // ÄNDRAT:
    @RequestMapping(value = "/list/{userid}", method = RequestMethod.GET)
    public ResponseEntity<TransactionListingDTO> getTransactions(@PathVariable Long userid , @RequestParam Long startPosition, @RequestParam Long endPosition) {
        TransactionListingDTO transactionListingDTO;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transactionListingDTO= transactionService.getTransactions(userid,startPosition,endPosition);

        if(transactionListingDTO != null){
            return new ResponseEntity<>(transactionListingDTO, HttpStatus.OK);
        }
        logger.info("RETURNING BAD REQUEST FOR SOME REASON!");
        return new ResponseEntity<>(new TransactionListingDTO(), HttpStatus.BAD_REQUEST);
    }
}
