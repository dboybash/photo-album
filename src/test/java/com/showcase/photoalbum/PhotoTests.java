/*
* Author: Shawn Bash
* Purpose: Contains test pertaining to Photos.java class for which
*          the photo data will be stored after parsing
*          Json response.
* */

package com.showcase.photoalbum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoTests {

    Photo photo = new Photo();

    @Test
    public void contextLoads() {
    }

    @Test
    public void testPhotoObjExists() throws Exception {
        assertNotNull(photo);
    }

    @Test
    public void testAlbumId() throws Exception {
        int albumId = 0;
        assertEquals(albumId, photo.getAlbumId());
        photo.setAlbumId(albumId + 1);
        assertEquals(albumId + 1, photo.getAlbumId());
    }

    @Test
    public void testId() throws Exception {
        int Id = 0;
        assertEquals(Id, photo.getId());
        photo.setId(Id + 1);
        assertEquals(Id + 1, photo.getId());
    }

    @Test
    public void testTitle() throws Exception {
        String title = "Test Title";
        photo.setTitle(title);
        assertEquals(title, photo.getTitle());
    }


}
