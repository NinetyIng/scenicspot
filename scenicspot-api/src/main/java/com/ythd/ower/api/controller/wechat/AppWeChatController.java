package com.ythd.ower.api.controller.wechat;

import com.ythd.ower.api.controller.BaseController;
import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.service.AppUserService;
import com.ythd.ower.wx.common.WechatUtil;
import com.ythd.ower.wx.model.WXPArticle;
import com.ythd.ower.wx.model.WXPReceivedEventMessage;
import com.ythd.ower.wx.model.WXPReceivedMessage;
import com.ythd.ower.wx.model.WXPReceivedTextMessage;
import com.ythd.ower.wx.model.WXPReplyNewsMessage;
import com.ythd.ower.wx.model.WXPReplyTextMessage;
import com.ythd.ower.wx.prototype.WXMessageType;
import com.ythd.ower.wx.prototype.WXPReceivedMessageParser;
import com.ythd.ower.wx.prototype.WXPReplyMessageEncapsulator;
import com.ythd.ower.wx.service.WeChatService;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 微信相关控制层
 * @author Administrator
 */
@RequestMapping(value="/appwx/")
@Controller
public class AppWeChatController extends BaseController{

  private static final Logger LOGGER = LoggerFactory.getLogger(AppWeChatController.class);

  @Resource(name="weChatService")
  private WeChatService weChatService;

  @Autowired
  private AppUserService appUserService;

  private String TOKEN = ConfigureManager.getWxConfig().getToken();

  @RequestMapping(value = "/chartReply")
  public void valid(HttpServletRequest request, HttpServletResponse response) {
    // 判断是否为校验请求
    String echostr = request.getParameter("echostr");
    if (EAString.isNullStr(echostr)) {
      try {
        parserMessage(request, response);
      } catch (Exception e) {
        LOGGER.error("信息解析失败",e);
        this.flushMessage("error", response);
      }
    } else {
      checkSignature(request, response);
    }
  }
  /**
   * 解析消息
   * @param request
   * @param response
   * @throws Exception
   */
  private void parserMessage(HttpServletRequest request,HttpServletResponse response) throws Exception {
    String receivedMessage = readStringFromInputStream(request);
    WXPReceivedMessageParser wmp = new WXPReceivedMessageParser();
    String msgType = wmp.parseWXPReceivedMessageType(receivedMessage);
    if (WXMessageType.TEXT.equals(msgType)) {
      WXPReceivedTextMessage message = wmp.parseWXPReceivedTextMessage(receivedMessage);
      textMessageHandler(message, response);
    } else if (WXMessageType.EVENT.equals(msgType)) {
      WXPReceivedEventMessage message = wmp.parseWXPReceivedEventMessage(receivedMessage);
      this.eventMessageHandler(message, response,request);
    } else {
      System.out.println("未知消息类型!");
    }

  }

  /**
   * 文本消息处理
   *
   * @param sourceMessage
   * @param response
   * @throws Exception
   */
  private void textMessageHandler(WXPReceivedTextMessage sourceMessage,HttpServletResponse response) throws Exception {
    PageData dto = weChatService.getResultByKeyWord(sourceMessage.getContent());
    pushMessage(sourceMessage,response,dto);
  }
  /**
   * 事件消息处理
   *
   * @param message
   * @param response
   * @throws Exception
   */
  private void eventMessageHandler(WXPReceivedEventMessage message,HttpServletResponse response,HttpServletRequest request) throws Exception {
    String openId = message.getFromUserId();
    if(message.getEvent().equalsIgnoreCase(WXMessageType.EVENT_VIEW)){
      return ;
    }
    if(message.getEvent().equalsIgnoreCase(WXMessageType.EVENT_SCAN)){
      return ;
    }
    if(message.getEvent().equalsIgnoreCase(WXMessageType.EVENT_UNSUBSCRIBE)){
      LOGGER.info("检查到用户取消关注公众号，该用户的openid是：{}",openId);
      appUserService.unsubscribe(openId);
      return ;
    }
    PageData result;
    if (message.getEvent().equalsIgnoreCase(WXMessageType.EVENT_SUBSCRIBE)) {
      LOGGER.info("检查到用户关注公众号");
      PageData pd=new PageData();
      pd.put(UserConstant.OPENID, openId);
      SimpleDateFormat sdf = new SimpleDateFormat(UserConstant.TIMEFORMAT_ONE);
      String subtime = sdf.format(new Date(message.getCreateTime()*1000L));
      pd.put("subscribe_time", subtime);
      JSONObject jsonObj = WechatUtil.readUserInfo(StringUtils.trim(openId));
      LOGGER.info("获取到用户信息{}",jsonObj);
      pd.put(UserConstant.WX_USERINFO, jsonObj.toString());
      appUserService.subscribe(pd);
      result = weChatService.getResultByKeyWord("关注时回复");
      LOGGER.info("获取到回复信息为{}",request);
      pushMessage(message,response,result);
    } else if (message.getEvent().equalsIgnoreCase(WXMessageType.EVENT_CLICK)) {
      PageData menu=weChatService.findMenuByKey(message.getEventKey());
      result = new PageData();
      int menuType=menu.getAsInt("menu_type");
      result.put("reply_type",menuType);
      result.put("reply_content",menu.get("menu_content"));
      pushMessage(message,response,result);
    }
  }
  /**
   * 消息回發
   * @param message
   * @param response
   * @param dto
   * @throws Exception
   */
  private void pushMessage(WXPReceivedMessage message, HttpServletResponse response, PageData dto) throws Exception {
    if(dto!=null){
      if(dto.getAsInt("reply_type") == 1){
        List<WXPArticle> articles = new ArrayList<>();
        JSONArray jArray=JSONArray.fromObject(dto.get("reply_content"));
        for(int i=0;i<jArray.size();i++){
          WXPArticle at=new WXPArticle();
          JSONObject json=jArray.getJSONObject(i);
          at.setTitle(json.getString("title"));
          at.setPictureURL(json.getString("img"));
          at.setDesciprtion(json.getString("desc"));
          int author=EAString.stringToInt(json.getString("author"), -1);
          String url=json.getString("url");
          url=url.trim();
          if(author==1){
            at.setArticleURL(WechatUtil.getAuthorUrl(url));
          }else{
            String[] arrSplit=url.split("[?]");
            if(arrSplit.length>1){
              at.setArticleURL(url+"&OpenId="+message.getFromUserId());
            }else{
              at.setArticleURL(url+"?OpenId="+message.getFromUserId());
            }
          }
          articles.add(at);
        }
        pushNewsMessage(message, response, articles);
      }else if(dto.getAsInt("reply_type")==0){
        pushTextMessage(message, response, dto.getAsString("reply_content"));
      }
    }else{
    }
  }

  /**
   * 回发图文消息
   * @param sourceMessage
   * @param response
   * @param articles
   */
  private void pushNewsMessage(WXPReceivedMessage sourceMessage,HttpServletResponse response,List<WXPArticle> articles) {
    WXPReplyNewsMessage message = new WXPReplyNewsMessage();
    message.setFromUserId(sourceMessage.getToUserId());
    message.setToUserId(sourceMessage.getFromUserId());
    message.setArticleAmount(1);
    message.setCreateTime(System.currentTimeMillis());
    message.setFuncFlag(1);
    message.setArticles(articles);
    WXPReplyMessageEncapsulator wme = new WXPReplyMessageEncapsulator();
    String messageStr = wme.getNewsXml(message);
    System.out.println("===微信===开始回发====图文========");
    flushMessage(messageStr, response);
  }
  /**
   * 回发文本信息
   * @param sourceMessage
   * @param response
   * @param contenStr
   */
  private void pushTextMessage(WXPReceivedMessage sourceMessage,HttpServletResponse response, String contenStr) {
    WXPReplyTextMessage message = new WXPReplyTextMessage();
    message.setFromUserId(sourceMessage.getToUserId());
    message.setToUserId(sourceMessage.getFromUserId());
    message.setCreateTime(System.currentTimeMillis());
    message.setFuncFlag(1);
    message.setMessageType("transfer_customer_service");
    message.setContent(contenStr);
    WXPReplyMessageEncapsulator wme = new WXPReplyMessageEncapsulator();
    String messageStr = wme.getTextXml(message);
    System.out.println("===微信===开始回发=====文本======="+messageStr);
    flushMessage(messageStr, response);
  }

  /**
   * 向请求端发送返回数据
   *
   * @param content
   */
  public void flushMessage(String content, HttpServletResponse response) {
    try {
      response.setHeader("Content-type", "text/html;charset=UTF-8");
      response.getWriter().print(content);
      response.getWriter().flush();
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  /**
   * 微信接口URL跳转
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "oauth2", method = RequestMethod.GET)
  public String wechatCallBack(HttpServletRequest request) throws Exception {
    try{
      PageData dto=getPageData();
      System.out.println("回调参数：：：："+dto);
      String code = request.getParameter("code");
      String openId=WechatUtil.getOpenId(code);
      /**
       * 将openid存到数据库
       */
      PageData pd = new PageData();
      pd.put("open_id", openId);
      JSONObject jsonObj = WechatUtil.readUserInfo(StringUtils.trim(openId));
      pd.put("jsonObj", jsonObj);
      dto.remove("code");
      dto.remove("state");
      dto.remove("actName");
      dto.remove("isappinstalled");
      dto.put(UserConstant.OPENID, openId);
      String redirectUrl=WechatUtil.getRedirectUrl(dto.getAsString("zcurl"), dto.getAsInt("dengHaoShu"));
      dto.remove("zcurl");
      dto.remove("dengHaoShu");
      redirectUrl=getActUri(redirectUrl,dto);
      return "redirect:"+redirectUrl;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }


  /**
   * 得到认证URL
   * @param url
   * @param dto
   * @return
   */
  @SuppressWarnings("unchecked")
  private String getActUri(String url,PageData dto){
    String[] arrSplit=url.split("[?]");
    if(arrSplit.length>1){
      url =  url+"&"+WechatUtil.getUrlParamsByMap(dto);
      return url;
    }else{
      return url+"?"+WechatUtil.getUrlParamsByMap(dto);
    }
  }

  /**
   * 校验接口
   *
   * @param request
   * @param response
   */
  private void checkSignature(HttpServletRequest request,
                              HttpServletResponse response) {
    try {
      String signature = request.getParameter("signature");
      String timestamp = request.getParameter("timestamp");
      String nonce = request.getParameter("nonce");
      String echostr = request.getParameter("echostr");
      String[] tmpArr = { TOKEN, timestamp, nonce };
      Arrays.sort(tmpArr);
      String tmpStr = EAString.ArrayToString(tmpArr);
      tmpStr = EAString.SHA1Encode(tmpStr);
      if (tmpStr.equalsIgnoreCase(signature)) {
        this.flushMessage(echostr, response);
      } else {
        this.flushMessage("error", response);
      }
    } catch (Exception e) {
      e.printStackTrace();
      flushMessage("error", response);
    }
  }
  /**
   * 读出接受到的消息
   *
   * @return
   * @throws Exception
   */
  private String readStringFromInputStream(HttpServletRequest request)
          throws Exception {
    // 得到HTTP输入流
    InputStream is = request.getInputStream();
    // 取HTTP请求流长度
    int size = request.getContentLength();
    // 用于缓存每次读取的数据
    byte[] buffer = new byte[size];
    // 用于存放结果的数组
    byte[] xmldataByte = new byte[size];
    int count = 0;
    int rbyte = 0;
    // 循环读取
    while (count < size) {
      // 每次实际读取长度存于rbyte中
      rbyte = is.read(buffer);
      for (int i = 0; i < rbyte; i++) {
        xmldataByte[count + i] = buffer[i];
      }
      count += rbyte;
    }
    is.close();
    // 解析为xml对象
    String requestStr = new String(xmldataByte, "UTF-8");
    return requestStr;
  }
}
