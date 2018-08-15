package com.ythd.ower.server.service.content;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.content.ContentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContentService extends EABaseService {

	@Autowired
	private ContentMapper contentMapper;

	@Override
	public EADao getDao() {
		return contentMapper;
	}

	/**
	 * 删除图片
	 */
	public void deleteImg(PageData pd){
		contentMapper.deleteImg(pd);
	}

	public List<PageData> selectContentAlbums(Integer contentId){
		return contentMapper.selectContentAlbums(contentId);
	}

	public void insertContentAlbums(PageData pd){
		contentMapper.insertContentAlbums(pd);
	}
	public void updateContentAlbums(PageData pd){
		contentMapper.updateContentAlbums(pd);
	}
	public void deleteContentAlbums(PageData pd){
		contentMapper.deleteContentAlbums(pd);
	}

}
