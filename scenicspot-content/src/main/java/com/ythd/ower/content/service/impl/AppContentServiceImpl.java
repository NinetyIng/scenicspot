package com.ythd.ower.content.service.impl;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.content.constant.ContantConstant;
import com.ythd.ower.content.constant.ContentCommentType;
import com.ythd.ower.content.dto.ContentDetailDto;
import com.ythd.ower.content.mapper.AppCommentMapper;
import com.ythd.ower.content.mapper.AppContentMpper;
import com.ythd.ower.content.dto.ContentListDto;
import com.ythd.ower.content.model.ContentCommentModel;
import com.ythd.ower.content.model.ContentModel;
import com.ythd.ower.content.service.AppContentService;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  新闻咨询服务类
 */
@Service
public class AppContentServiceImpl implements AppContentService {

   @Resource
   private AppContentMpper appContentMpper;

   @Resource
   private AppCommentMapper appCommentMapper;

    /**
     * 做缓存对象避免多次查库
     */
   private static List<ContentModel> FIXTOP_LIST;
    /**
     * 做缓存固顶所有id
     */
   static List<Integer> EXCLUDE_IDS = new ArrayList<>(8);

   @Override
   public ContentListDto contentList(Page page){

       if(CollectionUtils.isEmpty(FIXTOP_LIST)){
           FIXTOP_LIST = appContentMpper.findFixTopList(page.getPd());
       }
       if(CollectionUtils.isEmpty(EXCLUDE_IDS) && CollectionUtils.isNotEmpty(FIXTOP_LIST)){
           EXCLUDE_IDS.addAll(FIXTOP_LIST.stream().map(item -> item.getId()).collect(Collectors.toList()));
           page.getPd().put(ContantConstant.EXCLUDE_IDS,EXCLUDE_IDS);
       }
       return ContentListDto.builder().setFixTopList(FIXTOP_LIST)
               .setContentList(appContentMpper.findContentListByPage(page));
   }

    @Override
    public ContentDetailDto contentDetail(Page page) {
       return ContentDetailDto.builder().setContentModel(appContentMpper.findContentDetail(page.getPd()))
               .setComments(appCommentMapper.findCommentListByPage(page));
    }


    @Override
    public void addComment(ContentCommentModel model) {
       model.setStatus(ContentCommentType.UNAUDITED.getCode());
        model.setPutime(DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
       appCommentMapper.addComment(model);
       appContentMpper.addParseCount();
    }

    @Override
    public void addPrase(PageData pageData) {
        appCommentMapper.addPrase(pageData);
        appContentMpper.addParseCount();
    }
}
