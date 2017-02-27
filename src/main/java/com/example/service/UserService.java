package com.example.service;

import com.example.model.*;

/**
 * Created by dani on 2017-02-06.
 */

public interface UserService {
    UserDTO getUser(Long id);
    UserDTO login(UserAuthenticationDTO userAuthenticationDTO);
    UserDTO register(UserRegistrationDTO userRegistrationDTO);
    UserDTO update(UserRegistrationDTO userRegistrationDTO);
    UserUpdateDTO changePassword(String username, String email, String newPassword);

    //admin
    AdminUserDTO getUserByUsername(String username);
    AdminUserListingDTO getAdminUsers(Long startPosition, Long endPosition);
    AdminUserDTO registerAdminUser(AdminUserAdderDTO incomingUser);
    AdminUserDTO adminAccountActivation(Long id, boolean enable);
    AdminUserDTO adminUpdateUser(AdminUserDTO incomingUser);
}
