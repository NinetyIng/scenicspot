package com.ythd.ower.server.service.content;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EAUtil;
import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.server.mapper.content.AdvertMapper;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdvertService extends EABaseService {

	@Autowired
	private AdvertMapper advertMapper;

	@Autowired
	@Override
	public EADao getDao() {
		return advertMapper;
	}

	/**
	 * 广告列表
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> selectadvertlist(PageData pd) throws Exception {
		Page page = new Page();
		page.setPd(pd);
		return advertMapper.selectadvertlistByPage(page);
	}

	/**
	 * 浏览广告信息
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int advertbrowse(PageData pd) throws Exception {
		if (EAUtil.isEmpty(pd.getAsString("ad_id"))) {
			throw new Exception("请检查参数");
		}
		PageData data = advertMapper.selectById(pd.getAsInt("ad_id"));
		if (EAUtil.isNotEmpty(data)) {
			data.put("ad_page_views", data.getAsInt("ad_page_views") + 1);
			return advertMapper.update(data);
		}
		return 0;
	}

	/**
	 * 广告位置列表
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> advertpositionlist(PageData pd) throws Exception {
		Page page = new Page();
		page.setPd(pd);
		return advertMapper.selectpositionByPage(page);
	}

	/**
	 * 添加位置
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public boolean addadvertposition(PageData pd) throws Exception {
		Random rnd = new Random();
		int num = rnd.nextInt(89999) + 10000;
		pd.put("ap_code", num);
		pd.put("ap_create_time", EADate.getCurrentTime());
		int insert = advertMapper.insertposition(pd);
		if (insert > 0) {
			return true;
		} else {
			throw new Exception("出错了");
		}
	}

	/**
	 * 修改位置
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public boolean updateadvertposition(PageData pd) throws Exception {
		int update = advertMapper.updateposition(pd);
		if (update > 0) {
			return true;
		} else {
			throw new Exception("出错了");
		}
	}

	/**
	 * 删除位置
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public boolean deladvertposition(PageData pd) throws Exception {
		Page page = new Page();
		PageData descpd = new PageData();
		descpd.put("ad_apid", pd.getAsString("ap_id"));
		page.setPd(descpd);
		int dels = advertMapper.delete(page);
		if (dels > 0) {
			int del = advertMapper.deleteposition(pd);
			if (del > 0) {
				return true;
			} else {
				throw new Exception("出错了");
			}
		} else {
			throw new Exception("出错了");
		}

	}
}
