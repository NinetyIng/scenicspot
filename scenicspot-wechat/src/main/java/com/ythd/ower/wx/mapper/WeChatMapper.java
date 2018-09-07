package com.ythd.ower.wx.mapper;

import com.ythd.ower.common.dto.PageData;
import java.util.List;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/7
 * @version: $Revision$
 */
public interface WeChatMapper {

  /**新增
   * @param pd
   * @throws Exception
   */
  int insert(PageData pd);

  /**删除
   * @param pd
   * @throws Exception
   */
  int delete(PageData pd);

  /**修改
   * @param pd
   * @throws Exception
   */
  int update(PageData pd);


  /**
   * 通过Id获取数据
   * @param pd
   * @return
   */
  PageData findById(PageData pd);

  /**
   * 微信自動回復按關鍵詞查詢回復內容
   * @param pd
   * @return
   */
  PageData findByKeyWord(PageData pd);

  /**
   * 微信自動回復按關鍵詞查詢回復內容
   * @param pd
   * @return
   */
  PageData findByEventKey(PageData pd);

  /**匹配关键词
   * @param pd
   * @return
   * @throws Exception
   */
  PageData findByKw(PageData pd);

  /**列表
   * @param pd
   * @return
   * @throws Exception
   */
  List<PageData> list(PageData pd);

  /**列表(全部)
   * @param pd
   * @return
   * @throws Exception
   */
  List<PageData> listAll(PageData pd);

  /**批量删除
   * @param ArrayDATA_IDS
   * @throws Exception
   */
  void deleteAll(String[] ArrayDATA_IDS);

  /**列表(全部)
   * @param pd
   * @return
   * @throws Exception
   */
  List<PageData> datalistPage(PageData pd);


  /**
   * =======================================================================================================================================
   * 微信菜单
   * =======================================================================================================================================
   */
  void saveMenu(PageData pd);
  void deleteMenu(PageData pd);
  void editMenu(PageData pd);
  List<PageData> listMenu();
  List<PageData> listMenu(PageData pd);
  PageData findMenuById(int menuId);
  void deleteMenuById(PageData pd);
}
