package com.ythd.ower.data.dao;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;

import java.util.List;

public interface EADao {
	 List<PageData> selectByPage(Page parameter);
	 List<PageData> selectByMap(Page page);
	 List<PageData> selectByIds(PageData page);
	 PageData selectById(String id);
	 PageData selectById(Integer id);
	 int insert(PageData model);
	 int update(PageData model);
	 int delete(Page page);
	 int getCount(PageData model);
}
