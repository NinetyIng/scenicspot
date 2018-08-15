package com.ythd.ower.server.service.content;

import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.content.ContentCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContentCommentService  extends EABaseService {

	@Autowired
	private ContentCommentMapper contentCommentMapper;
	
	@Override
	public EADao getDao() {
		return contentCommentMapper;
	}

}
