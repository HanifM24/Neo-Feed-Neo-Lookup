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
        

        final String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+strstartdate+"&end_date="+strenddate+"&api_key="+ myApiKey;
        

        RestTemplate restTemplate = new RestTemplate();
        
        ObjectMapper mapper = new ObjectMapper();
        CloseApproachDatum closeApproachDatummm;
        near_earth_objects near_earth_objectsss = new near_earth_objects();
        near_earth_objectsss.close_approach_data = new ArrayList<CloseApproachDatum>();
        ArrayList<near_earth_objects>listmaster_near_earth_objectss = new ArrayList<near_earth_objects>();
        // ArrayList<master_near_earth_objects>listmaster_near_earth_objectsstartendtemp;
        Feed mapped = new Feed(){};
        ArrayList<CloseApproachDatum> close_approach_datas = new ArrayList<CloseApproachDatum>();
        mapped.near_earth_objects  = listmaster_near_earth_objectss;
        // mapped.near_earth_objects.
        
        //near_earth_objects inner_near_earth_objectss = new near_earth_objects();
        master_near_earth_objects master_near_earth_objectsstartdate = new master_near_earth_objects();
        try{
            ObjectMapper JSONnodemapper = new ObjectMapper();
            String  JsonRes = restTemplate.getForObject(uri, String.class);

            JsonNode node = mapper.readTree(JsonRes); 

            for(int i = 0; i<datesInRange.size(); i++)
            {
                JsonNode newjsnonode= node.get("near_earth_objects");
                ObjectMapper JSONDdemapperer = new ObjectMapper();

                ObjectMapper JSONDdemappererchild = new ObjectMapper();
                JsonNode again = newjsnonode.get(datesInRange.get(i));
                
                        
                for(int j = 0; j<again.size(); j++ )
                {
                    JsonNode closeapproachdatanode =  again.get(j).get("close_approach_data");
                     for(int k = 0; k<closeapproachdatanode.size(); k++ ){
                            JsonNode closeapproachdatum = closeapproachdatanode.get(k);
                            CloseApproachDatum closeApproachDatumm = JSONnodemapper.treeToValue(closeapproachdatum, CloseApproachDatum.class);
                            
                            near_earth_objectsss.close_approach_data.add(closeApproachDatumm);
                            System.out.println();


                        

                     }

                }
 
                    
                    System.out.println();

            }

            mapped.near_earth_objects = listmaster_near_earth_objectss;

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

        return calendar;
    }

}
