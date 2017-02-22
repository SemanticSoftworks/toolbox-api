package com.example.controllers;

import com.example.model.UserAuthenticationDTO;
import com.example.model.UserDTO;
import com.example.model.UserRegistrationDTO;
import com.example.model.UserUpdateDTO;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        UserDTO userDTO = userService.getUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // base 64 encoding
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<UserDTO> login(@RequestBody UserAuthenticationDTO incomingUser){
        logger.info("THIS says: username: "+incomingUser.getUsername() + " password: "+incomingUser.getPassword());

        UserDTO userDTO= userService.login(incomingUser);
        if(userDTO != null){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO incomingUser){
        logger.info("username of incoming user: "+incomingUser.getUsername());

        UserDTO userDTO= userService.register(incomingUser);
        if(userDTO != null){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/update" , method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRegistrationDTO incomingUser){

        UserDTO userDTO= userService.update(incomingUser);
        if(userDTO != null){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/forgotpassword", method = RequestMethod.POST)
    public ResponseEntity<UserUpdateDTO> changePassword(@RequestParam String username,@RequestParam String email, @RequestParam String newPassword){
        UserUpdateDTO userUpdateDTO = userService.changePassword(username,email,newPassword);
        if(userUpdateDTO != null){
            return new ResponseEntity<>(userUpdateDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserUpdateDTO(),HttpStatus.BAD_REQUEST);
    }
}
