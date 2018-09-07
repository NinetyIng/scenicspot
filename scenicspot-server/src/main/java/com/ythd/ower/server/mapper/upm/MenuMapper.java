package com.ythd.ower.server.mapper.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.data.entity.upm.Menu;
import java.util.List;

public interface MenuMapper extends EADao {

	 List<Menu> listSubMenuByParentId(String menuId);
	
	 void insertMenu(Menu menu);
	
	 PageData findMaxId(PageData pd);
	
	 void deleteMenuById(String menuId);
	
	 void updateMenu(Menu menu);
	
	 PageData editicon(PageData pd);
	
}
