package com.ythd.ower.server.mapper.content;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EADao;
import java.util.List;

public interface ContentMapper  extends EADao {
	
	List<PageData> selectlistByPage(Page pg);
	List<PageData> selectAllByPage(Page pg);
	PageData selectDetails(PageData pg);
	
	List<PageData> selectHomelist(PageData pg);

	List<PageData> selMaxLv(PageData pd);
	
	void deleteImg(PageData pd);
	/**
	 * 查询固定新闻   
	 * limit  限制条数
	 * exclude 排除的新闻
	 * @param pd
	 * @return
	 */
	List<PageData> selectTopLimit(PageData pd);
	/**
	 * 查询焦点新闻
	 * limit  限制条数
	 * exclude 排除的新闻
	 */
	List<PageData> selectFoucsLimit(PageData pd);
	
	/**
	 * 根据发布时间查询新闻 排序  先按固定级别降序  再按发布时间降序(不分页)
	 * limit 限制条数
	 * exclude 排除的新闻
	 */
	List<PageData> selectByPutime(PageData pd);
	
	/**
	 * 根据发布时间查询新闻  先按固定级别降序  再按发布时间降序(分页)
	 * exclude 排除的新闻
	 */
	List<PageData> selectByPutimePage(Page pd);
	
	/**
	 * 根据用户id和新闻id查询是否点赞
	 */
	List<PageData> selectThumbsup(PageData pd);
	
	/**
	 * 根据新闻id查询相冊
	 */
	List<PageData> selectContentAlbums(Integer contentId);
	
	void insertContentAlbums(PageData pd);
	
	void updateContentAlbums(PageData pd);
	
	void deleteContentAlbums(PageData pd);
	List<PageData> selectHotContent(PageData pd);
	PageData selectPrevOrNext(PageData pd);
	
}
