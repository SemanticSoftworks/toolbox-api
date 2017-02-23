package com.example.service.impl;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;
import com.example.service.AdminService;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-12.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private TransactionService transactionService;

    @Override
    public TransactionDTO updateTransaction(TransactionIdentifierDTO transactionIdentifierDTO) { return transactionService.updateTransaction(transactionIdentifierDTO); }

    @Override
    public TransactionDTO deleteTransactionById(Long id, String username, String password) { return transactionService.deleteTransactionById(id, username, password); }
}
