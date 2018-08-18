package com.ythd.ower.api.redirect;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 客户端跳转控制器 只负责跳转
 *
 */
@Controller
@RequestMapping("/jumplink/")
public class ClientRedirectController {

    @RequestMapping("home")
    public String homePage(){
        return "common/home";
    }

    @RequestMapping("contentList")
    public String contentList(){
        return "content/contentList";
    }

    @RequestMapping("productIndex")
    public String productIndex(){
        return "product/productIndex";
    }

    @RequestMapping("productList")
    public String productList(){
        return "product/productList";
    }

    @RequestMapping("ticketList")
    public String ticketList(){
        return "ticket/ticketList";
    }

}
