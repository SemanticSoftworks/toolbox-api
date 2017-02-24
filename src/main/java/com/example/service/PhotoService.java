package com.example.service;

import com.example.model.PhotoDTO;
import com.example.model.PhotoListingDTO;

/**
 * Created by Teddy on 2017-02-10.
 */
public interface PhotoService {

    PhotoDTO addPhoto(PhotoDTO photoToAdd);
    PhotoListingDTO getImgsOfAd(int adId);
}
