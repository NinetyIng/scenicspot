package com.ythd.ower.member.service;

import com.ythd.ower.common.dto.PageData;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public interface AppUserService {

  /**
   * 关注公众号
   * @param pd
   */
  void subscribe(PageData pd);

  /**
   * 取消关注公众号
   * @param openId
   */
  void unsubscribe(String openId);

}
