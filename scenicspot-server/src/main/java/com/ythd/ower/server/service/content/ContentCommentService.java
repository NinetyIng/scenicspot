package com.ythd.ower.server.service.content;
import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
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
