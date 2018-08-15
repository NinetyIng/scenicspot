package com.ythd.ower.server.mapper.scenic;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EADao;
import java.util.List;

public interface ScenicMapper extends EADao {

	/**
	 * 查询类目
	 * @param pd
	 * @return
	 */
	 List<PageData> categorySelectByMap(PageData pd);

	/**
	 * 类目更新
	 * @param pd
	 */
	 void categoryUpdate(PageData pd);

	/**
	 * 类目插入
	 * @param pd
	 */
	 void categoryInsert(PageData pd);

	/**
	 * 插入图片
	 * @param pd
	 */
	 void insertImg(PageData pd);

	/**
	 * 删除类目
	 * @param page
	 */
	 void deletecategory(Page page);


	/**
	 * 相册列表
	 * @param pd
	 * @return
	 */
	 List<PageData> selectImgByMap(PageData pd);
	
	/**
	 * 删除图片
	 * @param pd
	 */
	 void deleteImgsById(PageData pd);


}
