package com.ythd.ower.server.mapper.ticket;


import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EADao;
import java.util.List;

public interface TicketMapper  extends EADao {

	
	 List<PageData> categorySelectByMap(PageData pd);
	
	 void categoryUpdate(PageData pd);
	
	 void categoryInsert(PageData pd);
	
	 void categoryDel(PageData pd);
}
