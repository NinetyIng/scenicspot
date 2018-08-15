package com.ythd.ower.server.service.scenic;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.EATools;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.scenic.ScenicMapper;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScenicService  extends EABaseService {

	@Autowired
	private ScenicMapper scenicMapper;
	@Override
	public EADao getDao() {
		return scenicMapper;
	}
	
	/**
	 * ##################原子方法#############################
	 * @param pd
	 * @return
	 */
	public List<PageData> categorySelectByMap(PageData pd){
		return scenicMapper.categorySelectByMap(pd);
	}
	public void categoryUpdate(PageData pd){
		scenicMapper.categoryUpdate(pd);
	}
	public void categoryInsert(PageData pd){
		scenicMapper.categoryInsert(pd);
	}
	public void deletecategory(PageData pd){
		Page page= new Page();
		page.setPd(pd);
		scenicMapper.deletecategory(page);
	}
	
	public List<PageData> selectImgByMap(PageData pd){
		return scenicMapper.selectImgByMap(pd);
	}
	
	public void insertImg(PageData pd){
		scenicMapper.insertImg(pd);
	}

	public void deleteImgsById(PageData pd) {
		scenicMapper.deleteImgsById(pd);
	}

}
