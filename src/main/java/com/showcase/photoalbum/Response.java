/*
* Author: Shawn Bash
* Purpose: This class is used to process Json data received from the
*          web service located at "https://jsonplaceholder.typicode.com/photos".
*          High level design for methods used:
*          1. get Json body
*          2. group photos by AlbumId
*          3. sort photo list within each AlbumId
*          4. display photo albums formatted per requirements.
* */

package com.showcase.photoalbum;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Response {

    String url = "https://jsonplaceholder.typicode.com/photos";
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);


    public Response() {}

    // return the status code
    public HttpStatus getStatusCode() throws Exception {
        return responseEntity.getStatusCode();
    }

    // return Json body
    public Object getBody() {
        return responseEntity.getBody();
    }

    // populate Json data into a list for processing
    public List<Photo> getJson() {
        ResponseEntity<LinkedList<Photo>> photoResponse =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<LinkedList<Photo>>() {
                });
        return  photoResponse.getBody();
    }

    // group photos by AlbumID
    // order the photos by AlbumID (to ease later operations) and map using HashMap
    public Map<Integer, List<Photo>> GroupPhotosByAlbum(List<Photo> photoList) {
        Collections.sort(photoList, new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return o1.getAlbumId() > o2.getAlbumId() ?0:1;
            }
        });

        Map<Integer, List<Photo>> photosByAlbum = new HashMap<>();

        for (int i = 0; i < photoList.size(); i++) {
            if (photosByAlbum.containsKey(photoList.get(i).getAlbumId())){
                List<Photo> photoListObj = photosByAlbum.get(photoList.get(i).getAlbumId());
                photoListObj.add(photoList.get(i));
            } else {
                ArrayList<Photo> photoAlbum = new ArrayList<>();
                photoAlbum.add(photoList.get(i));
                photosByAlbum.put(photoList.get(i).getAlbumId(), photoAlbum);
            }
        }

        return photosByAlbum;
    }

    // sort the list of photos within each AlbumID (AlbumID is key in Map<k,v>)
    public Map<Integer, List<Photo>> SortPhotoGroup(Map<Integer, List<Photo>> unsortedPhotos){
        Map<Integer, List<Photo>> sortedPhotos = unsortedPhotos;
        for (Integer key : sortedPhotos.keySet()) {
//            System.out.println("key: " + key + " value: " + photosByAlbum.get(key));
            Collections.sort(sortedPhotos.get(key), new Comparator<Photo>() {
                @Override
                public int compare(Photo o1, Photo o2) {
                    return o1.getId() > o2.getId() ? 0 : 1;
                }
            });
        }

        return sortedPhotos;
    }

    // output data per exercise requirement to console
    public void displaySortedPhotosByAlbum(Map<Integer, List<Photo>> sortedPhotos){
        for (Integer key : sortedPhotos.keySet()) {
            System.out.println("> photo-album " + key );
            List<Photo> photoList = sortedPhotos.get(key);
            for (int i = 0; i < photoList.size(); i++) {
                System.out.println("[" + photoList.get(i).getId() + "] " + photoList.get(i).getTitle());
            }
        }

    }

}
