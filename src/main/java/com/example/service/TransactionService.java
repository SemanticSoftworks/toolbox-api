package com.example.service;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;
import com.example.model.TransactionListingDTO;

/**
 * Created by dani on 2017-02-09.
 */
public interface TransactionService {

    TransactionListingDTO getTransactions(Long userId, Long startPosition, Long endPosition, String username, String password);
    TransactionDTO getTransaction(Long transactionId, String username, String password);
    TransactionDTO addTransaction(TransactionIdentifierDTO transactionIdentifierDTO);
    TransactionDTO updateTransaction(TransactionIdentifierDTO transactionIdentifierDTO);
    TransactionDTO deleteTransactionById(Long id, String username, String password);
}
