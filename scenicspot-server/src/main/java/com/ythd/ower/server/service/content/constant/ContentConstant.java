package com.ythd.ower.server.service.content.constant;

/**
 *
 * Description:
 * @author: liujunbo
 * @since: 2018/7/31
 * @version: $Revision$
 */
public interface ContentConstant {

  String COMMON_MSG = "msg";

  String COMMON_PD = "pd";

  String CATEGORY_LIST = "categoryList";

  String SUBJECT_LIST = "subjectList";

  interface ContentStatus{
    /**
     * 草稿状态
     */
    String STATUS_DRAFT = "0";
    /**
     * 已发布
     */
    String STATUS_RELEASE = "1";
    /**
     * 未发布
     */
    String STATUS_NO_RELEASE = "2";
    /**
     * 被删除
     */
    String STATUS_DELETE = "3";
  }

  interface ModelType{
    /**
     * 单图模式
     */
    String SINGLE = "0";
    /**
     * 多图模式
     */
    String MANY = "1";

  }

  interface RequestType{
    String REQUEST_EDIT ="edit";
    String REQUEST_ADD = "add";
  }



  interface ContentFiled{

    String CONTENT_ID = "CONTENT_ID";

    /**
     * 文章状态
     */
    String STATE = "STATE";
    /**
     * 文章内容
     */
    String CONTENT = "CONTENT";

    /**
     * 是否推荐
     */
    String RECOMMEND = "RECOMMEND";
    /**
     * 是否热门
     */
    String ISHOT = "ISHOT";

    /**
     * 是否焦点
     */
    String ISFOCUS  = "ISFOCUS";

    /**
     * 固定级别
     */
    String TOPLV = "TOPLV";

    /**
     * 发布时间
     */
    String PUTTIME = "PUTTIME";

    /**
     * 模式类型
     */
    String MODEL_TYPE = "MODEL_TYPE";


    String T_IMG ="T_IMG";

    String CREATETIME = "CREATETIME";

    String CREATOR = "CREATOR";

    String  DISABLED = "DISABLED";

  }

}
