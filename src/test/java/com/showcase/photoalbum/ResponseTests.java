/*
* Author: Shawn Bash
* Purpose: Contains tests pertaining to Response.java class
*          for which processes data retrieved from the web
*          service located at "https://jsonplaceholder.typicode.com/photos"
* */

package com.showcase.photoalbum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseTests {

    Response response = new Response();
    ArrayList<Photo> testPhotosList = new ArrayList<>();
    int testAlbumCount;

    @Before
    public void setup() {
        testAlbumCount = 4;
        Photo photo1 = new Photo();
        photo1.setAlbumId(1);
        photo1.setId(1);
        photo1.setTitle("Photo1");

        Photo photo2 = new Photo();
        photo2.setAlbumId(1);
        photo2.setId(2);
        photo2.setTitle("Photo2");

        Photo photo3 = new Photo();
        photo3.setAlbumId(2);
        photo3.setId(3);
        photo3.setTitle("Photo3");

        Photo photo4 = new Photo();
        photo4.setAlbumId(2);
        photo4.setId(4);
        photo4.setTitle("Photo4");

        Photo photo5 = new Photo();
        photo5.setAlbumId(3);
        photo5.setId(5);
        photo5.setTitle("Photo5");

        Photo photo6 = new Photo();
        photo6.setAlbumId(4);
        photo6.setId(6);
        photo6.setTitle("Photo6");

        Photo photo7 = new Photo();
        photo7.setAlbumId(4);
        photo7.setId(7);
        photo7.setTitle("Photo7");

        testPhotosList.add(photo1);
        testPhotosList.add(photo3);
        testPhotosList.add(photo5);
        testPhotosList.add(photo6);
        testPhotosList.add(photo4);
        testPhotosList.add(photo7);
        testPhotosList.add(photo2);

//        for (Photo photo : testPhotosList) {
//            System.out.println(photo.toString());
//        }

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testResponseObjExists() {
        assertNotNull(response);
    }

    @Test
    public void testGetJson() throws Exception {
        // test good connection
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        // test response returns something
        List<Photo> testJsonResponse = response.getJson();
        assertNotNull(testJsonResponse);
    }

    @Test
    public void testGroupPhotosByAlbum() {

        Map<Integer, List<Photo>> testGroupPhotosByAlbum;

        // test GroupPhotosByAlbum returns populated map
        testGroupPhotosByAlbum = response.GroupPhotosByAlbum(testPhotosList);
        assertNotNull(testGroupPhotosByAlbum);

        // test if key count matches testAlbumCount
        assertEquals(testAlbumCount, testGroupPhotosByAlbum.size());

//        for (Integer key : testGroupPhotosByAlbum.keySet()) {
//            System.out.println("key: " + key + " value: " + testGroupPhotosByAlbum.get(key));
//        }

    }

    @Test
    public void testSortPhotoGroup() {
        boolean albumOrdered = false;
        boolean photosOrdered = false;

        Map<Integer, List<Photo>> testUnsortedPhotos = response.GroupPhotosByAlbum(testPhotosList);
        assertNotNull(testUnsortedPhotos);

        Map<Integer, List<Photo>> testSortedPhotos = response.SortPhotoGroup(testUnsortedPhotos);
        assertNotNull(testSortedPhotos);


        // test to verify albumID order
        int prevPhotoAlbum = -1;
        for (Integer key : testSortedPhotos.keySet()) {
            if (key > prevPhotoAlbum) {
                albumOrdered = true;
                prevPhotoAlbum = key;
            } else {
                return;
            }
        }
        assertTrue(albumOrdered);

        // test to verify Id order
        int prevPhotoId = -1;
        for (Integer key : testSortedPhotos.keySet()) {
            List<Photo> photoList = testSortedPhotos.get(key);
            for (int i = 0; i < photoList.size(); i++) {
                if (photoList.get(i).getId() > prevPhotoId) {
                    photosOrdered = true;
                    prevPhotoId = photoList.get(i).getId();
                } else {
                    return;
                }

            }
        }
        assertTrue(photosOrdered);
    }
}