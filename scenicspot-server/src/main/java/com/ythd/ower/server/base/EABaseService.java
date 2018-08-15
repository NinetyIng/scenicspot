package com.ythd.ower.server.base;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.EAUtil;
import java.util.List;

public abstract class EABaseService implements EAService{
	@Override
	public abstract EADao getDao();

	@Override
	public List<PageData> getByPage(Page parameter) {
		return getDao().selectByPage(parameter);
	}

	@Override
	public List<PageData> getByMap(PageData parameter) {
		Page page=new Page();
		page.setPd(parameter);
		return getDao().selectByMap(page);
	}

	@Override
	public PageData getOneByMap(PageData parameter) {
		Page page=new Page();
		page.setPd(parameter);
		List<PageData> list=getDao().selectByMap(page);
		if(EAUtil.isNotEmpty(list) && list.size() == 1){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 指定条件的数据总行数
	 * @return
	 */
	@Override
	public int getCount(PageData model){
		return getDao().getCount(model);
	}
	
	@Override
	public PageData getById(Integer parameter) {
		// TODO Auto-generated method stub
		return getDao().selectById(parameter);
	}

	@Override
	public PageData getById(String parameter) {
		// TODO Auto-generated method stub
		return getDao().selectById(parameter);
	}
	@Override
	public int create(PageData model) {
		// TODO Auto-generated method stub
		return getDao().insert(model);
	}

	@Override
	public int edit(PageData model) {
		// TODO Auto-generated method stub
		return getDao().update(model);
	}

	@Override
	public int delete(PageData parameter) {
		Page page=new Page();
		page.setPd(parameter);
		return getDao().delete(page);
	}

	@Override
	public List<PageData> getByIds(String ids){
		if(EAString.isNullStr(ids)){
			return null;
		}
		if(ids.substring(ids.length()-1).equals(",")){
			ids=ids.substring(0, ids.length()-1);
		}
		PageData page=new PageData();
		page.put("ids", "("+ids+")");
		return getDao().selectByIds(page);
	}
}
