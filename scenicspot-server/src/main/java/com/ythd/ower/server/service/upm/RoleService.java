package com.ythd.ower.server.service.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.entity.upm.Role;
import com.ythd.ower.server.base.EABaseService;
import com.ythd.ower.server.base.EADao;
import com.ythd.ower.server.mapper.upm.RoleMapper;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class RoleService extends EABaseService {

	@Resource(name="roleMapper")
	private RoleMapper roleMapper;
	
	@Override
	public EADao getDao() {
		return roleMapper;
	}
	/**列出此组下级角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Role> listAllRolesByPId(PageData pd) throws Exception {
		return roleMapper.listAllRolesByPId(pd);
	}
	
	/**通过id查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findObjectById(PageData pd) throws Exception {
		return roleMapper.selectById(pd.getAsString("ROLE_ID"));
	}
	
	/**添加
	 * @param pd
	 * @throws Exception
	 */
	public void add(PageData pd) throws Exception {
		roleMapper.insert(pd);
	}
	
	/**保存修改
	 * @param pd
	 */
	public int edit(PageData pd) {
		return roleMapper.update(pd);
	}
	
	/**删除角色
	 * @param ROLE_ID
	 * @throws Exception
	 */
	public void deleteRoleById(String ROLE_ID) throws Exception {
		roleMapper.deleteRoleById(ROLE_ID);
	}
	
	/**给当前角色附加菜单权限
	 * @param role
	 * @throws Exception
	 */
	public void updateRoleRights(Role role) throws Exception {
		roleMapper.updateRoleRights(role);
	}
	
	/**通过id查找
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(String ROLE_ID) throws Exception {
		Role role = new Role();
		PageData pd = roleMapper.selectById(ROLE_ID);
		if(pd != null && pd.size() >= 0){
			role.setADD_QX(pd.getAsString("ADD_QX"));
			role.setCHA_QX(pd.getAsString("CHA_QX"));
			role.setDEL_QX(pd.getAsString("DEL_QX"));
			role.setEDIT_QX(pd.getAsString("EDIT_QX"));
			role.setROLE_ID(pd.getAsString("ROLE_ID"));
			role.setROLE_NAME(pd.getAsString("ROLE_NAME"));
			role.setRIGHTS(pd.getAsString("RIGHTS"));
			role.setPARENT_ID(pd.getAsString("PARENT_ID"));
			role.setSCHOOL_ID(pd.getAsString("SCHOOL_ID"));
			return role;
		}else{
			return null;
		}
	}
	
	/**给全部子角色加菜单权限
	 * @param pd
	 * @throws Exception
	 */
	public void setAllRights(PageData pd) throws Exception {
		roleMapper.setAllRights(pd);
	}
	
	/**权限(增删改查)
	 * @param msg 区分增删改查
	 * @param pd
	 * @throws Exception
	 */
	public void saveB4Button(String msg,PageData pd) throws Exception {
		
		if("add_qx".equalsIgnoreCase(msg)){
			roleMapper.add_qx(pd);
		}else if("del_qx".equalsIgnoreCase(msg)){
			roleMapper.del_qx(pd);
		}else if("edit_qx".equalsIgnoreCase(msg)){
			roleMapper.edit_qx(pd);
		}else if("cha_qx".equalsIgnoreCase(msg)){
			roleMapper.cha_qx(pd);
		}
	}
}
