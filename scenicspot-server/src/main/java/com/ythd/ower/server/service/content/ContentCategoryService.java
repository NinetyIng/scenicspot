package com.ythd.ower.server.service.content;

import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.server.mapper.content.ContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentCategoryService extends EABaseService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	
	@Override
	public EADao getDao() {
		return contentCategoryMapper;
	}
}
