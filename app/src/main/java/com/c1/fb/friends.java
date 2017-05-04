package com.c1.fb;

/**
 * Created by anant bansal on 1/27/2017.
 */

public class friends {

    String name;
    String gender;
    String place;

    public friends(String name,String gender,String place)
    {
        this.name=name;
        this.gender=gender;
        this.place=place;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
