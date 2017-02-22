package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dani on 2017-02-10.
 */
@RestController
@RequestMapping("/admin")
public class AdminController{
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
}
