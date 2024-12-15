package com.example.Neo.Feed.Neo.Lookup.Model.Feed;

public class near_earth_objects {
    master_near_earth_objects master;
    public String year;

    // Constructor 
    near_earth_objects(String year) {
        this.master = new master_near_earth_objects();
        this.year = year;
    }
}

