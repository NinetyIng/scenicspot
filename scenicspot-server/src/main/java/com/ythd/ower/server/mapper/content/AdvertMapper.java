package com.ythd.ower.server.mapper.content;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EADao;
import java.util.List;


public interface AdvertMapper  extends EADao {
	
	List<PageData> selectadvertlistByPage(Page page);
	List<PageData> selectpositionByPage(Page page);
	List<PageData> selectpositionByMap(PageData pd);
	PageData selectpositionById(PageData pd);
	List<PageData> selectpositionByIds(PageData pd);
	int insertposition(PageData pd);
	int updateposition(PageData pd);
	int deleteposition(PageData pd);
}
