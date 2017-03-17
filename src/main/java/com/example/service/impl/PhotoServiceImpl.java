package com.example.service.impl;

import com.example.model.PhotoDTO;
import com.example.model.PhotoListingDTO;
import com.example.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Teddy on 2017-02-10.
 */
@Transactional
@Service
public class PhotoServiceImpl implements PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);
    private final String HOST = "http://192.168.99.100:8094";

    @Override
    public PhotoDTO addPhoto(PhotoDTO photoToAdd) {
        try {
            String url = HOST + "/photo/addphoto";
            RestTemplate restTemplate = new RestTemplate();

            photoToAdd = (restTemplate.postForObject(url, photoToAdd, PhotoDTO.class));
        } catch (HttpClientErrorException e) {
            logger.info("CATCH bad request addTransaction!");
            return null;
        }

        return photoToAdd;
    }

    /**
     * Gets the images of an ad.
     *
     * @param adId the ad id
     * @return an object containing the list of ads
     */
    @Override
    public PhotoListingDTO getImgsOfAd(int adId) {
        PhotoListingDTO photoListingDTO = new PhotoListingDTO();
        PhotoDTO[] photoDTO;

        try {
            String url = HOST + "/photo/getadphotos/{adId}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("adId", Integer.toString(adId));

            RestTemplate restTemplate = new RestTemplate();

            photoDTO = restTemplate.getForObject(url, PhotoDTO[].class, params);
            photoListingDTO.setPhotoList(Arrays.asList(photoDTO));

            // parsing error...
            //photoListingDTO = restTemplate.getForObject(url, PhotoListingDTO.class, params);
        } catch (HttpClientErrorException e) {
            logger.info("CATCH bad request getImgs!");
            return null;
        }

        return photoListingDTO;
    }
}
