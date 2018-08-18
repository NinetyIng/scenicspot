package com.ythd.ower.api.common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/")
public class AppHomeController {

    @RequestMapping("home")
    public String homePage(){
        return "common/home";
    }
}
