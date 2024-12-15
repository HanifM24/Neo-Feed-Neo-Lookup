package com.example.Neo.Feed.Neo.Lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Neo.Feed.Neo.Lookup.Model.Feed.Feed;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class APIController {
    
    
    @GetMapping("/feed")
    public String displayData() {
        final String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";

        RestTemplate restTemplate = new RestTemplate();
        
        ObjectMapper mapper = new ObjectMapper();
        Feed map = new Feed();
        try{
            map = restTemplate.getForObject(uri, Feed.class);
            //Feed map = mapper.readValue(result, Feed.class);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        System.out.println("result");
        return "result";
    }

}

