package com.ythd.ower.server.service.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.upm.ButtonRightsMapper;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ButtonRightService extends EABaseService {

	@Autowired
	private ButtonRightsMapper buttonRightsMapper;
	@Override
	public EADao getDao() {
		return buttonRightsMapper;
	}
	public List<PageData> listAll(PageData pd) {
		return buttonRightsMapper.listAllBrAndQxname(pd);
	}
	public PageData findById(PageData pd) {
		return buttonRightsMapper.selectById(pd.getAsString("FHBUTTON_ID"));
	}
	public void save(PageData pd) {
		buttonRightsMapper.insert(pd);
	}

}
