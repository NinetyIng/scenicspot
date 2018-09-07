package com.ythd.ower.wx.mapper;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;

public interface WechatMenuMapper extends EADao {
	/**
	 * 删除微信菜单
	 * @param id
	 * @return 
	 */
	int delete(String id);
	
	/**
	 * 根据条件查询总数
	 * @param pd
	 * @return 
	 */
	int getCount(PageData pd);
}