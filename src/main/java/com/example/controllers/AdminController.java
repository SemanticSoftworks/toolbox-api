package com.example.controllers;

import com.example.model.*;
import com.example.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<AdminUserListingDTO> getUsers(@RequestParam Long startPosition, @RequestParam Long endPosition){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUserListingDTO adminUserListingDTO = adminService.getUsers(startPosition, endPosition);
        return new ResponseEntity<>(adminUserListingDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<AdminUserDTO> registerAdminUser(@RequestBody AdminUserAdderDTO incomingUser){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUserDTO adminUserDTO = adminService.registerUser(incomingUser);
        return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/accountActivation/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AdminUserDTO> accountActivation(@PathVariable Long id , @RequestParam Boolean enable){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUserDTO adminUserDTO = adminService.accountActivation(id, enable);
        return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/user/update" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<AdminUserDTO> updateUser(@RequestBody AdminUserDTO incomingUser){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUserDTO adminUserDTO = adminService.updateUser(incomingUser);
        return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
    }


    @RequestMapping(value="/role", method = RequestMethod.GET)
    public ResponseEntity<RoleListingDTO> getRoles(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RoleListingDTO roleListingDTO = adminService.getRoles();
        return new ResponseEntity<>(roleListingDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/role", method = RequestMethod.POST)
    public ResponseEntity<RoleDTO> addRole(@RequestParam String role){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RoleDTO roleDTO = adminService.addRole(role);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/role/update", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<RoleDTO> updateRole(@RequestParam RoleDTO incomingRole){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RoleDTO roleDTO = adminService.updateRole(incomingRole);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/transaction" , method = RequestMethod.PUT, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO incomingTransaction){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public ResponseEntity<TransactionDTO> deleteTransaction(@PathVariable Long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TransactionDTO transactionDTO = adminService.deleteTransactionById(id);

        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }
}
