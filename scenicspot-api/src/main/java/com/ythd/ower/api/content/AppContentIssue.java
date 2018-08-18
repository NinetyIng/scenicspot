package com.ythd.ower.api.content;


import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.content.constant.ContantConstant;
import com.ythd.ower.content.dto.ContentDetailDto;
import com.ythd.ower.content.dto.ContentListDto;
import com.ythd.ower.content.model.ContentCommentModel;
import com.ythd.ower.content.service.AppContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 提供所有与文章有关接口
 */
@Controller
@RequestMapping("/api/content/")
public class AppContentIssue {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppContentIssue.class);
   @Autowired
   private AppContentService appContentService;

    /**
     * 新闻列表接口
     * @param requestParam
     * @return
     */
   @RequestMapping(value = "list",method = RequestMethod.POST)
   public GenericResponseDto list(@RequestBody PageData requestParam){
     LOGGER.info("请求文章列表接口，请求参数：{}", MapperUtil.toJson(requestParam));
         ContentListDto contentListDto = appContentService.contentList(Page.builder(requestParam));
       LOGGER.info("文章列表接口响应数据为{}", MapperUtil.toJson(contentListDto));
     return DtoUtils.getSuccessResponse(MapperUtil.toMap(MapperUtil.toJson(contentListDto)));
   }

    /**
     * 新闻详情接口
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public GenericResponseDto detail(@RequestBody PageData requestParam){
        LOGGER.info("请求文章详情接口，请求参数：{}", MapperUtil.toJson(requestParam));
        if(StringUtils.isEmpty(requestParam.getAsString(ContantConstant.CONTENT_ID))){
           throw new BizServiceException(ErrorCodesContants.PARAM_ERROR);
        }
        ContentDetailDto contentDetailDto = appContentService.contentDetail(Page.builder(requestParam));
        LOGGER.info("文章详情接口响应数据为{}", MapperUtil.toJson(contentDetailDto));
        return DtoUtils.getSuccessResponse(MapperUtil.toMap(MapperUtil.toJson(contentDetailDto)));
    }

    /**
     * 增加新闻评论接口
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "addComment",method = RequestMethod.POST)
    public GenericResponseDto addComment(@RequestBody PageData requestParam){
        LOGGER.info("用户请求添加评论接口请求参数：{}", MapperUtil.toJson(requestParam));
        appContentService.addComment(MapperUtil.map(MapperUtil.toJson(requestParam), ContentCommentModel.class));
        return DtoUtils.getSuccessResponse();
    }

    /**
     * 点赞接口
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "addPrase",method = RequestMethod.POST)
    public GenericResponseDto addPrase(@RequestBody PageData requestParam){
        LOGGER.info("用户请求点赞接口 请求参数{}", MapperUtil.toJson(requestParam));
        appContentService.addPrase(requestParam);
        return DtoUtils.getSuccessResponse();
    }

}
