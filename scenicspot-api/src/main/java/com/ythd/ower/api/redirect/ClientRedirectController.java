package com.ythd.ower.api.redirect;


import com.ythd.ower.b2c.constant.ProductConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("productDetail")
    public String productDetail(Model model,String productId){
        model.addAttribute(ProductConstant.PRODUCT_ID,productId);
        return "product/productDetail";
    }
    @RequestMapping("confirmOrder")
    public String confirmOrder(Model model,String productId){
        model.addAttribute(ProductConstant.PRODUCT_ID,productId);
        return "product/confirm-order";
    }
    @RequestMapping("productList")
    public String productList(Model model,String recommendOrder,String putimeOrder) {
        if (StringUtils.isNotEmpty(recommendOrder)){
            model.addAttribute(ProductConstant.RECOMMEND_ORDER,recommendOrder);
        }
        if (StringUtils.isNotEmpty(putimeOrder)){
            model.addAttribute(ProductConstant.PUTIME_ORDER,putimeOrder);
        }
        return "product/productList";
    }

    @RequestMapping("ticketList")
    public String ticketList(){
        return "ticket/ticketList";
    }

}
