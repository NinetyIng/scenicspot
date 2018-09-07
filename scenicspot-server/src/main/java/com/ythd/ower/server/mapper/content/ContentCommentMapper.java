package com.ythd.ower.server.mapper.content;


import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;

import java.util.List;

public interface ContentCommentMapper extends EADao {

	int deleteComment(PageData pd);
	
	int insertThumbsup(PageData pd);
	
	PageData selectThumbsupById(PageData pd);
	
	List<PageData> selectThumbsuplist(PageData pd);
}
