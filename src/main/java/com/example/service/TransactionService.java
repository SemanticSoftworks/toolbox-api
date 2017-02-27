package com.example.service;

import com.example.model.TransactionDTO;
import com.example.model.TransactionListingDTO;

/**
 * Created by dani on 2017-02-09.
 */
public interface TransactionService {

    TransactionListingDTO getTransactions(Long userId, Long startPosition, Long endPosition);
    TransactionDTO getTransaction(Long transactionId);
    TransactionDTO addTransaction(TransactionDTO transactionDTO);
    TransactionDTO updateTransaction(TransactionDTO transactionIdentifierDTO);
    TransactionDTO deleteTransactionById(Long id);
}
