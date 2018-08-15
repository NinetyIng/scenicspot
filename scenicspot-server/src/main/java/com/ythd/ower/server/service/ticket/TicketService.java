package com.ythd.ower.server.service.ticket;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.ticket.TicketMapper;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService  extends EABaseService {

	@Autowired
	private TicketMapper ticketMapper;
	@Override
	public EADao getDao() {
		return ticketMapper;
	}
	
	
	public List<PageData> categorySelectByMap(PageData pd){
		return ticketMapper.categorySelectByMap(pd);
	}
	
	public void  categoryUpdate(PageData pd){
		ticketMapper.categoryUpdate(pd);
	}
	
	public void categoryInsert(PageData pd){
		ticketMapper.categoryInsert(pd);
	}
	public void categoryDel(PageData pd){
		ticketMapper.categoryDel(pd);
	}

}
