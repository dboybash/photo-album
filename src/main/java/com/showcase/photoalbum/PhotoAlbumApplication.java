/*
* LT Tech Showcase - Photo Album
* Author: Shawn Bash
* Purpose: To consume data from the web service located at
* 		   "https://jsonplaceholder.typicode.com/photos" and
* 		   display the output in a console window.  The output
* 		   requirements are photos should be grouped by album.
* 		   and displayed by Id and Title.
* */

package com.showcase.photoalbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PhotoAlbumApplication {


    public static void main(String[] args) throws Exception {
        Response response = new Response();
        SpringApplication.run(PhotoAlbumApplication.class);

        try {
            // process Json response
            List<Photo> photos = response.getJson();

            // group photos by album
            Map<Integer, List<Photo>> photosGroupedByAlbum = response.GroupPhotosByAlbum(photos);

            // sort photos
            Map<Integer, List<Photo>> photosSortedInAlbum = response.SortPhotoGroup(photosGroupedByAlbum);

            // display photos by album in console
            response.displaySortedPhotosByAlbum(photosSortedInAlbum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}