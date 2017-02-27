package com.example.service.impl;

import com.example.model.AdminUserAdderDTO;
import com.example.model.AdminUserDTO;
import com.example.model.AdminUserListingDTO;
import com.example.model.TransactionDTO;
import com.example.service.AdminService;
import com.example.service.TransactionService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-12.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionIdentifierDTO) { return transactionService.updateTransaction(transactionIdentifierDTO); }

    @Override
    public TransactionDTO deleteTransactionById(Long id) { return transactionService.deleteTransactionById(id); }

    @Override
    public AdminUserListingDTO getUsers(Long startPosition, Long endPosition) { return userService.getAdminUsers(startPosition, endPosition); }

    @Override
    public AdminUserDTO registerUser(AdminUserAdderDTO incomingUser) { return userService.registerAdminUser(incomingUser); }

    @Override
    public AdminUserDTO accountActivation(Long id, boolean enable) { return userService.adminAccountActivation(id, enable); }

    @Override
    public AdminUserDTO updateUser(AdminUserDTO incomingUser) { return userService.adminUpdateUser(incomingUser); }
}
