package com.example.service.impl;

import com.example.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dani on 2017-02-09.
 */
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService{}
