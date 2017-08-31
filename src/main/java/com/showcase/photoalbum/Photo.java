/*
* Author: Shawn Bash
* Purpose: This class is used to create Photo objects based on data received
*          from the web service located at "https://jsonplaceholder.typicode.com/photos".
*          Per project requirements, only AlbumId, Id, and title meta data are used in
*          process output.
* */

package com.showcase.photoalbum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private int albumId, id;
    private String title;

    public Photo() {
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album " + albumId + ": Id= " + id + ", Title= " + title;
    }

}
