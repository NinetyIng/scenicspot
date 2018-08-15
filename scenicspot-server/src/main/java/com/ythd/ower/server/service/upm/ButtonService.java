package com.ythd.ower.server.service.upm;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.upm.ButtonMapper;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ButtonService extends EABaseService {

	
	@Autowired
	private ButtonMapper buttonMapper;
	@Override
	public EADao getDao() {
		return buttonMapper;
	}
	public List<PageData> listAll(PageData pd) {
		return buttonMapper.listAll(pd);
	}
	public void save(PageData pd) {
		buttonMapper.insert(pd);
	}
	public List<PageData> list(Page page) {
		return buttonMapper.selectByPage(page);
	}
}
