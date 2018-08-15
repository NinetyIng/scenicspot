package com.ythd.ower.server.service.content;

import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
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
