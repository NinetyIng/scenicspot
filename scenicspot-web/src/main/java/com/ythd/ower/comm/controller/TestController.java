package com.ythd.ower.comm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping("index")
    public String index(){
        return "test";
    }

    public static void main(String[] args) {
        String ss = "{\"base\":{\"appId\":\"wxbbbf48bf77396096\",\"appKey\":\"0a435a6272b04aab7cee6dec3258e34b\",\"mchtId\":\"1457494302\"},\"payConfig\":{\"payTitle\":\"都匀秦汉影视城订单支付\",\"payAppId\":\"wxbda2b96691cc5f54\",\"payAppKey\":\"4ADSF48796X59F5A64C54VAS5454S54D\",\"payMchtId\":\"1405197602\",\"notifyUrl\":\"http://liujunbo.tunnel.2bdata.com/app/pay\"},\"urlConfig\":{\"accessTokenUrl\":\"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}\",\"jsapiUrl\":\"https://api.weixin.qq.com/cgi-bin/ticket/getticket？access_token={access_token}&type=jsapi\",\"userListUrl\":\"https://api.weixin.qq.com/cgi-bin/user/get?access_token={access_token}\",\"userInfoUrl\":\"https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&&openid={openid}&lang=zh_CN\",\"openIdUrl\":\"https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code\",\"createMenuUrl\":\"https://api.weixin.qq.com/cgi-bin/menu/create?access_token={access_token}\",\"templateListUrl\":\"https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token={access_token}\",\"sendTemplateUrl\":\"https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={access_token}\",\"menuInfoUrl\":\"https://api.weixin.qq.com/cgi-bin/menu/get?access_token={access_token}\",\"unifiedOrderUrl\":\"https://api.mch.weixin.qq.com/pay/unifiedorder\",\"tgqrcodeUrl\":\"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={access_token}\"}}";
        JSONObject object = JSON.parseObject(ss);
        System.out.println(object);
    }
}
