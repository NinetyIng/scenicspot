package com.ythd.ower.wx.service;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.wx.mapper.WechatMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WechatMenuService extends EABaseService {
	@Autowired
	private WechatMenuMapper wechatMenuMapper;
	
	@Override
	public EADao getDao(){
		return wechatMenuMapper;
	}
	
	/**
	 * 根菜单
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listRootMenu()throws Exception{
		Page page=new Page();
		PageData pd=new PageData();
		pd.put("parent_id", "0");
		page.setPd(pd);
		return wechatMenuMapper.selectByMap(page);
	}
	/**
	 * 
	 * @param parent_id
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listSubMenu(int parent_id)throws Exception{
		Page page=new Page();
		PageData pd=new PageData();
		pd.put("parent_id", parent_id);
		page.setPd(pd);
		return wechatMenuMapper.selectByMap(page);
	}
	
	/**
	 * 删除段位制项目目录小分类
	 * @param dto
	 * @return 
	 */
	public int delete(String id){
		return wechatMenuMapper.delete(id);
	}
	
	public int getCount(PageData pd){
		return wechatMenuMapper.getCount(pd);
	}
	
}