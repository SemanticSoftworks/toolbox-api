package com.example.service.impl;

import com.example.model.UserAuthenticationDTO;
import com.example.model.UserDTO;
import com.example.model.UserRegistrationDTO;
import com.example.model.UserUpdateDTO;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dani on 2017-02-06.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDTO getUser(Long id) {
        String url = "http://localhost:8091/user/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, UserDTO.class, params);
    }

    @Override
    public UserDTO login(UserAuthenticationDTO userAuthenticationDTO) {
        UserDTO userDTO = null;

        try {
            String url = "http://localhost:8091/user/login";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userAuthenticationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request login!"); return null; }

        return userDTO;
    }

    @Override
    public UserDTO register(UserRegistrationDTO userRegistrationDTO) {
        UserDTO userDTO = null;

        try {
            String url = "http://localhost:8091/user/register";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userRegistrationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request register!"); return null; }

        return userDTO;
    }

    @Override
    public UserDTO update(UserRegistrationDTO userRegistrationDTO) {
        UserDTO userDTO = null;

        try {
            String url = "http://localhost:8091/user/update";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userRegistrationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request update!"); return null; }

        return userDTO;
    }

    @Override
    public UserUpdateDTO changePassword(String username, String email, String newPassword) {
        UserUpdateDTO userUpdateDTO=null;

        try {
            String url = "http://localhost:8091/user/forgotpassword?"+"username="+username+"&"+"email="+email+"&"+"newPassword="+newPassword;

            RestTemplate restTemplate = new RestTemplate();
            userUpdateDTO = restTemplate.postForObject(url, new UserUpdateDTO() ,UserUpdateDTO.class);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request change password!"); return null; }

        return userUpdateDTO;
    }
}
