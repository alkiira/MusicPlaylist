package org.example;
public class Song {
    private final int id;
    private String title;
    private String artist;
    private String album;
    private int duration;

    public Song(int id, String title, String artist, String album, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String updatedTitle) {
        this.title = updatedTitle;
    }

    public void setArtist(String updatedArtist) {
        this.artist = updatedArtist;
    }

    public void setAlbum(String updatedAlbum) {
        this.album = updatedAlbum;
    }

    public void setDuration(int updatedDuration) {
        this.duration = updatedDuration;
    }
}