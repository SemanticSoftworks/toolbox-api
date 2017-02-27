package com.example.service;

import com.example.model.*;

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
    RoleListingDTO getRoles();
    RoleDTO addRole(String role);
    RoleDTO updateRole(RoleDTO incomingRole);
}
