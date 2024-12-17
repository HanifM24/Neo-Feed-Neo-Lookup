package com.example.Neo.Feed.Neo.Lookup;

import java.nio.file.LinkPermission;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Neo.Feed.Neo.Lookup.Model.Feed.Feed;
import com.example.Neo.Feed.Neo.Lookup.Model.Feed.master_near_earth_objects;
import com.example.Neo.Feed.Neo.Lookup.Model.Feed.near_earth_objectsF.CloseApproachDatum;
import com.example.Neo.Feed.Neo.Lookup.Model.Feed.near_earth_objectsF.near_earth_objects;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@RequestMapping("/api")
public class APIController {
    
    
    @GetMapping("/feed")
    public String displayData() throws ParseException {
        // for realese
        //final String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";
        // please commend this
        String myApiKey = "qc8pEkcnwwxLUzV0WoedFI6rqEwQBtT8XfUC7bqY";
        List<String> datesInRange = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
       

        String strstartdate = "2015-09-07";
        String strenddate = "2015-09-12";

        Date startdate = formatter.parse(strstartdate);
        Date enddate = formatter.parse(strenddate);
    

        Calendar calendar =  getCalendarWithoutTime(startdate);
        Calendar endCalendar = getCalendarWithoutTime(enddate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            String resultstr =  new SimpleDateFormat("yyyy-MM-dd").format(result);
            datesInRange.add(resultstr);
            calendar.add(Calendar.DATE, 1);
          }
        datesInRange.add(strenddate);
        

        final String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+strstartdate+"&end_date="+strstartdate+"&api_key="+ myApiKey;
        

        RestTemplate restTemplate = new RestTemplate();
        
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<near_earth_objects>listmaster_near_earth_objectss = new ArrayList<near_earth_objects>();
        // ArrayList<master_near_earth_objects>listmaster_near_earth_objectsstartendtemp;
        Feed map = new Feed();
        CloseApproachDatum closeApproachDatumstart = new CloseApproachDatum();
        ArrayList<CloseApproachDatum> close_approach_datas = new ArrayList<CloseApproachDatum>();
        //near_earth_objects inner_near_earth_objectss = new near_earth_objects();
        master_near_earth_objects master_near_earth_objectsstartdate = new master_near_earth_objects();
        try{
            ObjectMapper JSONnodemapper = new ObjectMapper();
            String  JsonRes = restTemplate.getForObject(uri, String.class);

            JsonNode node = mapper.readTree(JsonRes); 

            for(int i = 0; i<datesInRange.size(); i++)
            {
                JsonNode newjsnonode= node.get("near_earth_objects").get(datesInRange.get(i));
                ObjectMapper JSONDdemapperer = new ObjectMapper();
                for(int j = 0; j<newjsnonode.size(); i++)
                    {
                        
                        ObjectMapper JSONDdemappererchild = new ObjectMapper();
                        JsonNode again = newjsnonode.get(j);
                        JsonNode closeapproachnode =  again.get("close_approach_data");
                        
                        for(int k = 0; k<closeapproachnode.size(); k++ )
                        {
                            CloseApproachDatum closeApproachDatum = JSONDdemappererchild.treeToValue(closeapproachnode.get(k), CloseApproachDatum.class);
                            close_approach_datas.add(closeApproachDatum);
                        }
                        near_earth_objects inner_near_earth_objectss = JSONDdemapperer.treeToValue(again, near_earth_objects.class);
                        listmaster_near_earth_objectss.add(inner_near_earth_objectss);
                        
                    }
                    
                    System.out.println();

            }
            map.element_count = node.path("element_count").path(JsonRes).asInt();
            map.near_earth_objects = listmaster_near_earth_objectss;
            //map.links

            
            
            
            //JsonNode newjsnonodeenddate = node.get("near_earth_objects").get(enddate);


            
            //master_near_earth_objects mObjects  =JSONnodemapper.treeToValue(newjsnonodestartdate, master_near_earth_objects.class);
            //listmaster_near_earth_objectsstartstarttemp =JSONnodemapper.convertValue(node.path("element_count").path("JsonRes"), listmaster_near_earth_objectsstartstarttemp);
            //String test = node.path("master_near_earth_objects").toString();   
            // map.near_earth_objects= new list_master_near_earth_objects(){
            //     listmaster_near_earth_objectsstartstart = new ArrayList<master_near_earth_objects>();
            // };
            //map.near_earth_objects.listmaster_near_earth_objectsstartend = JSONnodemapper.treeToValue( node.path(startdate),  map.near_earth_objects.listmaster_near_earth_objectsstartend );
            //map.near_earth_objects.listmaster_near_earth_objectsstartend = node.path(startdate).



            // for(map.near_earth_objects.listmaster_near_earth_objectsstartend:){

            // }

            // for(JsonNode jsonnodea : node.path("near_earth_objects")){
            //     map.near_earth_objects = new list_master_near_earth_objects{};
            // }
            //map.near_earth_objects = JSONnodemapper.convertValue(node.path("near_earth_objects"), map.near_earth_objects) .
            //map.links = node.path("links").
            // String street = rootNode.path("address").path("street").asText();
            // String city = rootNode.path("address").path("city").asText();
            //assertThat(product.getDetails().get("audioConnector").asText()).isEqualTo("none");
            // for (Int i = 0; i<map.near_earth_objects.; i++) {
                
            // }
            //Feed map = mapper.readValue(result, Feed.class);
            System.out.println();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        System.out.println("result");
        return "result";
    }

    private static Calendar getCalendarWithoutTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // calendar.set(Calendar.HOUR, 0);
        // calendar.set(Calendar.HOUR_OF_DAY, 0);
        // calendar.set(Calendar.MINUTE, 0);
        // calendar.set(Calendar.SECOND, 0);
        // calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
