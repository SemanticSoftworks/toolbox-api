package com.example.service;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;

/**
 * Created by dani on 2017-02-12.
 */
public interface AdminService {
    TransactionDTO updateTransaction(TransactionIdentifierDTO transactionIdentifierDTO);
    TransactionDTO deleteTransactionById(Long id, String username, String password);
}
