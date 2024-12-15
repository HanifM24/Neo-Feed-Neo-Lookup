package com.example.Neo.Feed.Neo.Lookup.Model.Feed;

import java.util.ArrayList;

import com.example.Neo.Feed.Neo.Lookup.Model.Feed.near_earth_objectsF.CloseApproachDatum;
import com.example.Neo.Feed.Neo.Lookup.Model.Feed.near_earth_objectsF.EstimatedDiameter;
import com.example.Neo.Feed.Neo.Lookup.Model.Feed.near_earth_objectsF.Links;

public class master_near_earth_objects {
    public Links links;
    public String id;
    public String neo_reference_id;
    public String name;
    public String nasa_jpl_url;
    public String absolute_magnitude_h;
    public EstimatedDiameter estimated_diameter;
    public String is_potentially_hazardous_asteroid;
    public ArrayList<CloseApproachDatum> close_approach_data;
    public String is_sentry_object;

    //constructor
    public master_near_earth_objects(){
        this.links = new Links();
        this.id = new String();
        this.neo_reference_id = new String();
        this.name =new String();
        this.nasa_jpl_url = new String();
        this.absolute_magnitude_h = new String();
        this.estimated_diameter = new EstimatedDiameter();
        this.is_potentially_hazardous_asteroid = new String();
        this.close_approach_data = new ArrayList<CloseApproachDatum>();
        this.is_sentry_object = new String();



    }

    
}
