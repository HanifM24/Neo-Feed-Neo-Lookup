package com.example.Neo.Feed.Neo.Lookup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/ui")
public class UIController {
    
    
    @GetMapping("/MainPage")
    public String displayData() {
        String message = "Welcome to GeeksForGeeks";
        return "Page";
    }
}


// @Controller
// public class InnerMgainController {
//     @RequestMapping("/api/example")
//     @ResponseBody
//     public String displayData() {
//         String message = "Welcome to GeeksForGeeks";
//         return "Page";
//     }
    
// }