package com.ythd.ower.wx.service;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.wx.mapper.WeChatMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/7
 * @version: $Revision$
 */
@Service
public class WeChatService {

  @Resource
  private WeChatMapper weChatMapper;

  /**新增
   * @param dto
   */
  public int save(PageData dto){
    return weChatMapper.insert(dto);
  }

  /**删除
   * @param dto
   * @throws Exception
   */
  public int delete(PageData dto){
    return weChatMapper.delete(dto);
  }

  /**修改
   * @param dto
   * @throws Exception
   */
  public int edit(PageData dto){
    return weChatMapper.update(dto);
  }

  /**列表
   * @param dto
   * @return
   * @throws Exception
   */
  public List<PageData> list(PageData dto){
    return weChatMapper.datalistPage(dto);
  }

  /**列表(全部)
   * @param dto
   * @return
   * @throws Exception
   */
  public List<PageData> listAll(PageData dto){
    return weChatMapper.listAll(dto);
  }

  /**通过id获取数据
   * @param dto
   * @return
   * @throws Exception
   */
  public PageData findById(PageData dto){
    return weChatMapper.findById(dto);
  }

  /**批量删除
   * @param ArrayDATA_IDS
   * @throws Exception
   */
  public void deleteAll(String[] ArrayDATA_IDS){
    weChatMapper.deleteAll(ArrayDATA_IDS);
  }

  /**匹配关键词
   * @param dto
   * @return
   * @throws Exception
   */
  public PageData findByKw(PageData dto){
    return weChatMapper.findByKw(dto);
  }

  public PageData getResultByKeyWord(String KeyWord){
    PageData dto = new PageData();
    dto.put("key_word", KeyWord);
    return weChatMapper.findByKeyWord(dto);
  }

  public PageData getSubscribeResult(){
    PageData dto = new PageData();
    dto.put("key_word", "关注时回复");
    return weChatMapper.findByKeyWord(dto);
  }

  public PageData getEventResult(String eventKey){
    PageData dto = new PageData();
    dto.put("key_word", eventKey);
    return weChatMapper.findByEventKey(dto);
  }

  /**
   * =======================================================================================================================================
   * 微信菜单
   * =======================================================================================================================================
   */
  public void saveMenu(PageData dto)throws Exception{
    weChatMapper.saveMenu(dto);
  }
  public void editMenu(PageData dto)throws Exception{
    weChatMapper.editMenu(dto);
  }
  public void deleteMenu(PageData dto)throws Exception{
    weChatMapper.delete(dto);
  }
  public List<PageData> listAllMenu()throws Exception{
    return weChatMapper.listMenu();
  }

  public List<PageData> listSubMenu(int parent_id)throws Exception{
    PageData dto = new PageData();
    dto.put("parent_id", parent_id);
    return weChatMapper.listMenu(dto);
  }

  public List<PageData> listRootMenu()throws Exception{
    PageData dto = new PageData();
    dto.put("parent_id", "0");
    return weChatMapper.listMenu(dto);
  }
  public PageData findMenuById(int menuId)throws Exception{
    return weChatMapper.findMenuById(menuId);
  }

  public PageData findMenuByKey(String menuKey)throws Exception{
    PageData dto = new PageData();
    dto.put("menu_key", menuKey);
    List<PageData> list = weChatMapper.listMenu(dto);
    if(list!=null&list.size()>0){
      return list.get(0);
    }
    return null;
  }

  public void deleteMenuById(PageData dto) throws Exception{
    weChatMapper.deleteMenuById(dto);

  }
}
