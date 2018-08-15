package com.ythd.ower.server.mapper.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.base.EADao;
import java.util.List;

public interface ButtonMapper extends EADao {

	 List<PageData> listAll(PageData pd);

	 void deleteAll(String[] arrayDATA_IDS);

	 List<PageData> findByCondition(PageData pd);
}
