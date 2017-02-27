package com.example.service;

import com.example.model.AdminUserAdderDTO;
import com.example.model.AdminUserDTO;
import com.example.model.AdminUserListingDTO;
import com.example.model.TransactionDTO;

/**
 * Created by dani on 2017-02-12.
 */
public interface AdminService {
    TransactionDTO updateTransaction(TransactionDTO transactionIdentifierDTO);
    TransactionDTO deleteTransactionById(Long id);

    AdminUserListingDTO getUsers(Long startPosition, Long endPosition);
    AdminUserDTO registerUser(AdminUserAdderDTO incomingUser);
    AdminUserDTO accountActivation(Long id, boolean enable);
    AdminUserDTO updateUser(AdminUserDTO incomingUser);
}
