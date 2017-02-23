package com.example.service.impl;

import com.example.model.TransactionDTO;
import com.example.model.TransactionIdentifierDTO;
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
    public TransactionListingDTO getTransactions(Long userId, Long startPosition, Long endPosition, String username, String password) {
        TransactionListingDTO transactionListingDTO;

        try {
            String url = "http://localhost:8092/transaction/{id}?startPosition="+startPosition+"&endPosition="+endPosition+"&username="+username+"&password="+password;
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", userId.toString());

            RestTemplate restTemplate = new RestTemplate();
           transactionListingDTO = restTemplate.getForObject(url, TransactionListingDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getTransaction!"); return null; }
        logger.info("THIS WORKS?");
        return transactionListingDTO;
    }

    @Override
    public TransactionDTO getTransaction(Long transactionId, String username, String password) {
        TransactionDTO transactionDTO;

        try {
            String url = "http://localhost:8092/transaction/{id}?username="+username+"&password="+password;
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", transactionId.toString());

            RestTemplate restTemplate = new RestTemplate();
            transactionDTO = restTemplate.getForObject(url, TransactionDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getTransaction!"); return null; }
        catch (Exception e){logger.info("connection error getTransaction"); return null;}

        return transactionDTO;
    }

    @Override
    public TransactionDTO addTransaction(TransactionIdentifierDTO transactionIdentifierDTO) {
        TransactionDTO transactionDTO;

        try {
            String url = "http://localhost:8092/transaction/";
            RestTemplate restTemplate = new RestTemplate();

            transactionDTO = (restTemplate.postForObject(url, transactionIdentifierDTO, TransactionDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request addTransaction!"); return null; }
        catch (Exception e){logger.info("connection error addTransaction"); return null;}

        return transactionDTO;
    }
}
