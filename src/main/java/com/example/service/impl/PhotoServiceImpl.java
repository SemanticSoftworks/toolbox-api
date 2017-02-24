package com.example.service.impl;

import com.example.model.PhotoDTO;
import com.example.model.PhotoListingDTO;
import com.example.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teddy on 2017-02-10.
 */
@Transactional
@Service
public class PhotoServiceImpl implements PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);


    @Override
    public PhotoDTO addPhoto(PhotoDTO photoToAdd) {
        try {
            String url = "http://localhost:8093/photo/addphoto";
            RestTemplate restTemplate = new RestTemplate();

            photoToAdd = (restTemplate.postForObject(url, photoToAdd, PhotoDTO.class));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request addTransaction!"); return null; }

        return photoToAdd;
    }

    @Override
    public PhotoListingDTO getImgsOfAd(int adId) {
       PhotoListingDTO photoListingDTO;

        try {
            String url = "http://localhost:8093/photo/getadphotos/{adId}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", Integer.toString(adId));

            RestTemplate restTemplate = new RestTemplate();
            photoListingDTO = restTemplate.getForObject(url, PhotoListingDTO.class, params);
        }catch(HttpClientErrorException e ){ logger.info("CATCH bad request getImgs!"); return null; }

        return photoListingDTO;
    }
}
