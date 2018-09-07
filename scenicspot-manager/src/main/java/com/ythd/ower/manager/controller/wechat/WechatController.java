package com.ythd.ower.manager.controller.wechat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.properties.PropertiesFactory;
import com.ythd.ower.common.properties.PropertiesFile;
import com.ythd.ower.common.properties.PropertiesHelper;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.wx.common.WechatUtil;
import com.ythd.ower.wx.service.WeChatService;
import com.ythd.ower.wx.service.WechatMenuService;
import com.ythd.ower.wx.service.WechatReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sys/wechat/")
public class WechatController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);


    @Autowired
    private WechatReplyService wechatReplyService;
    @Autowired
    private WechatMenuService wechatMenuService;
    @Autowired
    private WeChatService weChatService;
    private static final PropertiesHelper PROPERTIESHELPER = PropertiesFactory
            .getPropertiesHelper(PropertiesFile.SYS);

    /**
     * 关注时回复
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "subscribe.do")
    public ModelAndView subscribe() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData dto = this.getPageData();
        try {
            dto.put("key_word", "关注时回复");
            PageData data = weChatService.findByKw(dto);
            if (data == null) {
                data = new PageData();
                data.put("key_word", "关注时回复");
                data.put("reply_type", "0");
                data.put("reply_content", "欢迎关注");
                data.put("status", 1);
                data.put("creator", getAdminName());
                data.put("crate_time", EADate.date2Str(new Date()));
                weChatService.save(data);
            }
            mv.addObject("pd", data);
            mv.setViewName("/wechat/subscribe");
        } catch (Exception e) {
            LOGGER.error("访问关注时回复发生异常，异常信息", e);
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * 保存关注时回复
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveSubscribe.do")
    public void saveSubscribe(HttpServletResponse response) throws Exception {
        PageData pd = new PageData();
        pd.put("key_word", "关注时回复");
        PageData oldData = weChatService.findByKw(pd);
        boolean isExist = oldData != null;
        pd = this.getPageData();
        if (oldData == null) {
            oldData = new PageData();
        }
        oldData.put("key_word", "关注时回复");
        oldData.put("reply_type", pd.get("reply_type"));
        oldData.put("reply_content", getRequest().getParameter("data"));
        oldData.put("status", 1);
        oldData.put("creator", getAdminName());
        oldData.put("crate_time", EADate.date2Str(new Date()));
        try {
            if (isExist) {//数据库内存在
                weChatService.edit(oldData);
                this.outJson(response, REQUEST_SUCCESS, "更新成功", pd);
            } else {
                weChatService.save(oldData);
                this.outJson(response, REQUEST_SUCCESS, "更新成功", pd);
            }
        } catch (Exception e) {
            LOGGER.error("保存关注时回复发生异常，异常信息", e);
            this.outJson(response, REQUEST_FAILS, "数据保存时发生错误", pd);
        }
    }

    /**
     * 自动回复列表
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "reply_list.do")
    public ModelAndView replyList(Page page) {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<PageData> varList = wechatReplyService.getByPage(page);
        mv.addObject("dataList", varList);
        mv.addObject("page", page);
        mv.setViewName("/wechat/reply_list");
        return mv;
    }

    /**
     * 编辑自动回复(新增也用这个方法)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "edit_reply.do")
    public ModelAndView editReply(ModelMap model) {
        ModelAndView mv = this.getModelAndView();
        String id = getRequest().getParameter("id");
        mv.addObject("dataModel", wechatReplyService.getById(EAString.stringToInt(id, 0)));
        if (EAString.stringToInt(id, 0) > 0) {
            mv.setViewName("/wechat/edit_news");
        } else {
            mv.setViewName("/wechat/create_news");
        }
        return mv;
    }

    /**
     * 保存自动回复
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "save_reply.do")
    public void saveReply(HttpServletResponse response) {
        PageData data = this.getPageData();
        String jsonStr = getRequest().getParameter("data");
        String key_word = getRequest().getParameter("key_word");
        data.put("key_word", key_word);
        data.put("reply_type", 1);
        data.put("reply_content", jsonStr);
        data.put("status", 1);
        if (data.getAsInt("id") <= 0) {//编辑,而非新增
            data.put("creator", getAdminName());
            data.put("create_time", EADate.getCurrentTime());
            wechatReplyService.create(data);
            this.outJson(response, REQUEST_SUCCESS, "新增成功", data);
        } else {
            wechatReplyService.edit(data);
            this.outJson(response, REQUEST_SUCCESS, "修改成功", data);
        }
    }

    /**
     * 删除回复
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "del_reply.do")
    public String del_reply(HttpServletResponse response) throws Exception {
        PageData pd = this.getPageData();
        wechatReplyService.delete(pd);
        return "redirect:/sys/wechat/reply_list";
    }


    /**
     * ====================================================================================================================================
     * 微信菜单
     * ====================================================================================================================================
     */

    /**
     * 菜单列表
     *
     * @return
     */
    @RequestMapping(value = "menu_list.do")
    public ModelAndView menuList() {
        ModelAndView mv = this.getModelAndView();
        try {
            List<PageData> menus = wechatMenuService.listRootMenu();
            for (int i = 0; i < menus.size(); i++) {
                menus.get(i).put("subMenu", wechatMenuService.listSubMenu(menus.get(i).getAsInt("id")));
            }
            mv.addObject("menus", menus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("/wechat/menu_list");
        return mv;
    }

    /**
     * 编辑菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit_menu.do")
    public ModelAndView editMenu() throws Exception {
        ModelAndView mv = this.getModelAndView();
        String id = getRequest().getParameter("id");
        mv.addObject("menus", wechatMenuService.listRootMenu());
        if (!EAString.isNullStr(id)) {
            mv.addObject("dataModel", wechatMenuService.getById(EAString.stringToInt(id, 0)));
        }
        mv.setViewName("/wechat/edit_menu");
        return mv;
    }
    /**
     * 保存菜单
     *
     * @param response
     */
    @RequestMapping(value = "save_menu.do")
    public void saveMenu(HttpServletResponse response) {
        PageData data = this.getPageData();
        PageData pd = new PageData();
        pd.put("parent_id", '0');
        Integer count = wechatMenuService.getCount(pd);
        if (data.getAsInt("id") > 0) {
            int parent_id = Integer.parseInt(data.get("parent_id").toString());
            if (parent_id != 0) {
                wechatMenuService.edit(data);
                this.outJson(response, REQUEST_SUCCESS, "修改成功", data);
            } else {
                if (count > 3) {
                    this.outJson(response, REQUEST_FAILS, "修改失败，已存在三个一级菜单！！", null);
                } else {
                    wechatMenuService.edit(data);
                    this.outJson(response, REQUEST_SUCCESS, "修改成功", data);
                }
            }
        } else {
            int parent_id = Integer.parseInt(data.get("parent_id").toString());
            if (parent_id != 0) {
                data.put("menu_key", "event_key_" + EAString.getSnString());
                data.put("creator", getAdminName());
                data.put("create_time", new Date());
                wechatMenuService.create(data);
                this.outJson(response, REQUEST_SUCCESS, "保存成功", data);
            } else {
                if (count >= 3) {
                    this.outJson(response, REQUEST_FAILS, "请勿继续添加一级菜单，只能存在三个一级菜单！", null);
                } else {
                    data.put("menu_key", "event_key_" + EAString.getSnString());
                    data.put("creator", getAdminName());
                    data.put("create_time", new Date());
                    wechatMenuService.create(data);
                    this.outJson(response, REQUEST_SUCCESS, "保存成功", data);
                }
            }
        }
    }

    /**
     * 发布菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "releaseMenu.do")
    public void releaseMenu(HttpServletResponse response) {
        JSONArray jArray = new JSONArray();
        try {
            List<PageData> menus = wechatMenuService.listRootMenu();
            for (int i = 0; i < menus.size(); i++) {
                menus.get(i).put("subMenu", wechatMenuService.listSubMenu(menus.get(i).getAsInt("id")));
            }
            for (int i = 0; i < menus.size(); i++) {
                PageData rootMenu = menus.get(i);
                List<PageData> subMenus = rootMenu.getAsList("subMenu");
                JSONObject json = new JSONObject();
                json.put("name", rootMenu.get("menu_name"));
                if (subMenus != null && subMenus.size() > 0) {
                    JSONArray sms = new JSONArray();
                    for (int j = 0; j < subMenus.size(); j++) {
                        sms.add(getJson(subMenus.get(j)));
                    }
                    json.put("sub_button", sms);
                } else {
                    json = getJson(rootMenu);
                }
                jArray.add(json);
            }
            JSONObject button = new JSONObject();
            button.put("button", jArray);
            String msg = createMenu(button.toString());
            if (EAString.equals(msg, "OK")) {
                this.outJson(response, REQUEST_SUCCESS, "菜单已成功发布到微信", button.toString());
            } else {
                this.outJson(response, REQUEST_SUCCESS, "菜单发布到微信时发生错误,错误信息:" + msg, button.toString());
            }
        } catch (Exception e) {
            this.outJson(response, REQUEST_FAILS, "菜单发布到微信时发生错误,错误信息:" + e.getMessage(), null);
            e.printStackTrace();
        }
    }

    /**
     * 创建菜单
     */
    public static String createMenu(String params) throws Exception {
        //从接口项目获取accessToken
        String jsonStr =  WechatUtil.createMenu(params);
        JSONObject object = JSONObject.fromObject(jsonStr);
        return object.getString("errmsg");
    }



    /**
     * 序列化一个菜单为json
     *
     * @param pd
     * @return
     */
    private JSONObject getJson(PageData pd) {
        JSONObject json = new JSONObject();
        json.put("name", pd.get("menu_name"));
        //0回复文字 1跳转链接 2回复图文
        if (pd.getAsInt("menu_type") == 2) {//跳转地址
            json.put("type", "view");
            if (pd.getAsInt("author") == 0) {
                json.put("url", pd.getAsString("menu_content"));
            } else {
                json.put("url", WechatUtil.getAuthorUrl(pd.getAsString("menu_content")));
            }
        } else {
            json.put("type", "click");
            json.put("key", pd.getAsString("menu_key"));
        }
        return json;
    }

    /**
     * 删除菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delMenu.do")
    public void delMenu(HttpServletResponse response) throws Exception {
        PageData pd = this.getPageData();
        try {
            List<PageData> subMenus = wechatMenuService.listSubMenu(pd.getAsInt("id"));
            if (subMenus != null && subMenus.size() > 0) {
                this.outJson(response, REFUSE_ACCESS, "失败,菜单下有子菜单,请先移除所有子菜单", pd);
            } else {
                wechatMenuService.delete(pd);
                this.outJson(response, REQUEST_SUCCESS, "菜单已从微信端移除", pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.outJson(response, REQUEST_FAILS, "菜单从微信端移除时发生错误,错误信息:" + e.getMessage(), null);
        }
    }


}