package com.example.service.impl;

import com.example.model.TransactionDTO;
import com.example.model.TransactionListingDTO;
import com.example.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dani on 2017-02-09.
 */
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService{

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public TransactionListingDTO getTransactions(Long userId, Long startPosition, Long endPosition) {
        TransactionListingDTO transactionListingDTO;

        try {
            String url = "http://localhost:8092/transaction/list/{id}?startPosition="+startPosition+"&endPosition="+endPosition;
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", userId.toString());

            RestTemplate restTemplate = new RestTemplate();
           transactionListingDTO = restTemplate.getForObject(url, TransactionListingDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getTransaction!"); return null; }

        return transactionListingDTO;
    }

    @Override
    public TransactionDTO getTransaction(Long transactionId) {
        TransactionDTO transactionDTO;

        try {
            String url = "http://localhost:8092/transaction/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", transactionId.toString());

            RestTemplate restTemplate = new RestTemplate();
            transactionDTO = restTemplate.getForObject(url, TransactionDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getTransaction!"); return null; }

        return transactionDTO;
    }

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionIdentifierDTO){
        TransactionDTO transactionDTO;

        try {
            String url = "http://localhost:8092/transaction/";
            RestTemplate restTemplate = new RestTemplate();

            transactionDTO = (restTemplate.postForObject(url, transactionIdentifierDTO, TransactionDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request addTransaction!"); return null; }

        return transactionDTO;
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionIdentifierDTO) {

        try {
            String url = "http://localhost:8092/transaction/admin/update";
            Map<String, String> params = new HashMap<String, String>();
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.put(url,transactionIdentifierDTO,params);
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request updateTransaction!"); return null; }

        return new TransactionDTO();
    }

    @Override
    public TransactionDTO deleteTransactionById(Long id){
        try {
            String url = "http://localhost:8092/transaction/admin/{id}";

            Map<String, String> params = new HashMap<String, String>();
            params.put("id", id.toString());

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request deleteTransaction!"); return null; }

        return new TransactionDTO();
    }
}
