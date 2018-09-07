package com.ythd.ower.server.mapper.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.data.entity.upm.Role;
import java.util.List;

public interface RoleMapper extends EADao {
	
	 List<Role> listAllRolesByPId(PageData pd);
	
	 void deleteRoleById(String roleId);
	
	 void updateRoleRights(Role role);
	
	 void setAllRights(PageData pd);

	 void add_qx(PageData pd);
	
	 void del_qx(PageData pd);
	
	 void edit_qx(PageData pd);
	
	 void cha_qx(PageData pd);
	
	 Role getRoleById(String id);
	/**
	 * 查询系统管理员组角色
	 */
	 List<Role> getSysRole();
	
}
