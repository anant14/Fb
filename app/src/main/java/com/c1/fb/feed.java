package com.c1.fb;

/**
 * Created by anant bansal on 1/27/2017.
 */

public class feed {

    String feed;
    String up;

    public feed(String feed,String up) {
        this.feed=feed;
        this.up=up;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public void setUp(String up) {
        this.up = up;
    }
    public String getFeed() {
        return feed;
    }

    public String getUp() {
        return up;
    }
}
