package com.example.service;

import com.example.model.UserAuthenticationDTO;
import com.example.model.UserDTO;
import com.example.model.UserRegistrationDTO;
import com.example.model.UserUpdateDTO;

/**
 * Created by dani on 2017-02-06.
 */

public interface UserService {
    UserDTO getUser(Long id);
    UserDTO login(UserAuthenticationDTO userAuthenticationDTO);
    UserDTO register(UserRegistrationDTO userRegistrationDTO);
    UserDTO update(UserRegistrationDTO userRegistrationDTO);
    UserUpdateDTO changePassword(String username, String email, String newPassword);
}
