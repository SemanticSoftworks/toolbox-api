package com.example.controllers;

import com.example.model.PhotoDTO;
import com.example.model.PhotoListingDTO;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Teddy on 2017-02-13.
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/addphoto", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<PhotoDTO> addPhoto(@RequestBody PhotoDTO photo) {

        PhotoDTO photoCheck = photoService.addPhoto(photo);
        if(photoCheck != null){
            return new ResponseEntity<>(photoCheck, HttpStatus.OK);
        }

        return new ResponseEntity<>(photo, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getadphotos/{adId}", method = RequestMethod.GET)
    public ResponseEntity<PhotoListingDTO> getImgsOfAd(@PathVariable int adId) {

        PhotoListingDTO photoListingDTO;
        photoListingDTO = photoService.getImgsOfAd(adId);

        return new ResponseEntity<>(photoListingDTO, HttpStatus.OK);
    }
}
