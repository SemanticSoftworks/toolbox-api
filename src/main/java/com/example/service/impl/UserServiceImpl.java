package com.example.service.impl;

import com.example.model.*;
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
    private static final String PORT = "8091";
    private static final String HOST = "http://192.168.99.100:" + PORT;

    @Override
    public UserDTO getUser(Long id) {
        String url = HOST + "/user/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, UserDTO.class, params);
    }

    @Override
    public UserDTO login(UserAuthenticationDTO userAuthenticationDTO) {
        UserDTO userDTO = null;

        try {
            String url = HOST + "/user/login";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userAuthenticationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request login!"); return null; }
        catch (Exception e){logger.info("connection error login"); return null;}

        return userDTO;
    }

    @Override
    public UserDTO register(UserRegistrationDTO userRegistrationDTO) {
        UserDTO userDTO = null;

        try {
            String url = HOST + "/user/register";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userRegistrationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request register!"); return null; }
        catch (Exception e){logger.info("connection error register"); return null;}

        return userDTO;
    }

    @Override
    public UserDTO update(UserRegistrationDTO userRegistrationDTO) {
        UserDTO userDTO = null;

        try {
            String url = HOST + "/user/update";
            RestTemplate restTemplate = new RestTemplate();

            userDTO = (restTemplate.postForObject(url, userRegistrationDTO, UserDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request update!"); return null; }
        catch (Exception e){logger.info("connection error update"); return null;}

        return userDTO;
    }

    @Override
    public UserUpdateDTO changePassword(String username, String email, String newPassword) {
        UserUpdateDTO userUpdateDTO=null;

        try {
            String url = HOST + "/user/forgotpassword?"+"username="+username+"&"+"email="+email+"&"+"newPassword="+newPassword;

            RestTemplate restTemplate = new RestTemplate();
            userUpdateDTO = restTemplate.postForObject(url, new UserUpdateDTO() ,UserUpdateDTO.class);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request change password!"); return null; }

        return userUpdateDTO;
    }

    @Override
    public AdminUserDTO getUserByUsername(String username) {
        AdminUserDTO adminUserDTO;

        try {
            String url = HOST + "/user/username?username="+username;
            Map<String, String> params = new HashMap<String, String>();

            RestTemplate restTemplate = new RestTemplate();
            adminUserDTO = restTemplate.getForObject(url, AdminUserDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request user by username!"); return null; }

        return adminUserDTO;
    }


    // ADMIN stuff
    @Override
    public AdminUserListingDTO getAdminUsers(Long startPosition, Long endPosition) {
        AdminUserListingDTO adminUserListingDTO;

        try {
            String url = HOST + "/user/admin?startPosition=" + startPosition + "&endPosition=" + endPosition;
            Map<String, String> params = new HashMap<String, String>();
            params.put("startPosition", startPosition.toString());
            params.put("endPosition", endPosition.toString());

            RestTemplate restTemplate = new RestTemplate();
            adminUserListingDTO = restTemplate.getForObject(url, AdminUserListingDTO.class);
        } catch (HttpClientErrorException e) {
            logger.info("CATCH bad request getAdminU SERS!");
            return null;
        }

        return adminUserListingDTO;
    }

    @Override
    public AdminUserDTO registerAdminUser(AdminUserAdderDTO incomingUser) {
       AdminUserDTO adminUserDTO;

        try {
            String url = HOST + "/user/admin";

            RestTemplate restTemplate = new RestTemplate();
            adminUserDTO = restTemplate.postForObject(url, incomingUser ,AdminUserDTO.class);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request change password!"); return null; }

        return adminUserDTO;
    }

    @Override
    public AdminUserDTO adminAccountActivation(Long id, boolean enable) {

        try {
            String url = HOST + "/user/admin/accountActivation/{id}?enable="+enable;
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", id.toString());

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(url,"",params);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request updateTransaction!"); return null; }

        return new AdminUserDTO();
    }

    @Override
    public AdminUserDTO adminUpdateUser(AdminUserDTO incomingUser) {
        AdminUserDTO adminUserDTO;

        try {
            String url = HOST + "/user/admin/update";

            RestTemplate restTemplate = new RestTemplate();
            adminUserDTO = restTemplate.postForObject(url, incomingUser ,AdminUserDTO.class);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request change password!"); return null; }

        return adminUserDTO;
    }

    @Override
    public RoleListingDTO getRoles() {

        RoleListingDTO roleListingDTO;

        try {
            String url = HOST + "/user/admin/role";
            Map<String, String> params = new HashMap<String, String>();

            RestTemplate restTemplate = new RestTemplate();
            roleListingDTO = restTemplate.getForObject(url, RoleListingDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getAdminU SERS!"); return null; }
         return roleListingDTO;
    }

    @Override
    public RoleDTO addRole(String role) {
        RoleDTO roleDTO;

        try {
            String url = HOST + "/user/admin/role?role="+role;

            RestTemplate restTemplate = new RestTemplate();
            roleDTO = restTemplate.postForObject(url, new RoleDTO(),RoleDTO.class);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request change password!"); return null; }

        return roleDTO;
    }

    @Override
    public RoleDTO updateRole(RoleDTO incomingRole) {
        try {
            String url = HOST + "/user/admin/role";
            Map<String, String> params = new HashMap<String, String>();
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.put(url,incomingRole,params);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request updateTransaction!"); return null; }

        return incomingRole;
    }
}
