package com.ythd.ower.server.mapper.upm;


import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.entity.upm.Admin;
import com.ythd.ower.server.base.EADao;
import java.util.List;
public interface AdminMapper extends EADao {

	 void updateLastLogin(PageData pd);
	
	 PageData getUserInfo(PageData pd);
	
	 List<PageData> getUserByCondition(PageData pd);
	
	 Admin getUserAndRoleById(PageData pd);
	
	 List<PageData> listAllUserByRoldId(PageData pd);
	
	 void delete(PageData pd);
	
	 void deleteBatch(String[] ids);
}
