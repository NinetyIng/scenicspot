package com.ythd.ower.server.mapper.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;

import java.util.List;

public interface ButtonMapper extends EADao {

	 List<PageData> listAll(PageData pd);

	 void deleteAll(String[] arrayDATA_IDS);

	 List<PageData> findByCondition(PageData pd);
}
