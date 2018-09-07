package com.ythd.ower.data.dao;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;

import java.util.List;

public interface EAService {
	 EADao getDao();
	 List<PageData> getByPage(Page parameter);
	 List<PageData> getByMap(PageData parameter);
	 List<PageData> getByIds(String ids);
	 PageData getOneByMap(PageData parameter);
	 PageData getById(Integer parameter);
	 PageData getById(String parameter);
	 int create(PageData model);
	 int edit(PageData model);
	 int delete(PageData model);
	 int getCount(PageData parameter);
}
