package com.ythd.ower.server.mapper.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;

import java.util.List;

public interface ButtonRightsMapper extends EADao {

	 List<PageData> listAllBrAndQxname(PageData pd);
}
