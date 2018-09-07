package com.ythd.ower.server.service.upm;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EATools;
import com.ythd.ower.common.tools.MD5;
import com.ythd.ower.common.tools.WebUtil;
import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.data.entity.upm.Admin;
import com.ythd.ower.data.entity.upm.Menu;
import com.ythd.ower.data.entity.upm.Role;
import com.ythd.ower.server.mapper.upm.AdminMapper;
import com.ythd.ower.server.mapper.upm.ButtonMapper;
import com.ythd.ower.server.mapper.upm.ButtonRightsMapper;
import com.ythd.ower.server.mapper.upm.RoleMapper;
import com.ythd.ower.server.upm.Jurisdiction;
import com.ythd.ower.server.upm.RightsHelper;
import com.ythd.ower.server.upm.contants.Const;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
public class AdminService extends EABaseService {

	@Resource(name= "adminMapper")
	private AdminMapper adminMapper;

	@Resource(name="roleMapper")
	private RoleMapper roleMapper;

	@Resource(name="buttonMapper")
	public ButtonMapper buttonMapper;
	
	@Resource(name="buttonRightsMapper")
	private ButtonRightsMapper buttonRightsMapper;

	@Resource(name="menuService")
	private MenuService menuService;

	@Override
	public EADao getDao() {
		return adminMapper;
	}
    
	/**
	 * 登录逻辑处理
	 * @throws Exception 
	 */
	public void doLogin(PageData pd) throws Exception{
		    String KEYDATA[] = pd.getString("KEYDATA").split(",ea,");
			Session session = Jurisdiction.getSession();
			String USERNAME = KEYDATA[0];	//登录过来的用户名
			String PASSWORD  = KEYDATA[1];	//登录过来的密码
			pd.put("USERNAME", USERNAME);
//			String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//密码加密
			String passwd = MD5.md5(PASSWORD);
			pd.put("PASSWORD", passwd);
			pd = adminMapper.getUserInfo(pd);	//根据用户名和密码去读取用户信息
			if(pd != null){
				pd.put("LAST_LOGIN", EADate.getCurrentTime());
				pd.put("IP", WebUtil.getIp().toString());
				adminMapper.updateLastLogin(pd);
				Admin user = new Admin();
				user.setUSER_ID(pd.getString("USER_ID"));
				user.setUSERNAME(pd.getString("USERNAME"));
				user.setPASSWORD(pd.getString("PASSWORD"));
				user.setNAME(pd.getString("NAME"));
				user.setRIGHTS(pd.getString("RIGHTS"));
				user.setROLE_ID(pd.getString("ROLE_ID"));
				user.setLAST_LOGIN(pd.getString("LAST_LOGIN"));
				user.setIP(pd.getString("IP"));
				user.setSTATUS(pd.getString("STATUS"));
			/*	user.setTEACHER_ID(pd.getAsString("TEACHER_ID"));
				user.setSCHOOL_ID(pd.getAsString("SCHOOL_ID"));*/
				session.setAttribute(Const.SESSION_USER, user);			//把用户信息放session中
				//shiro加入身份验证
				Subject subject = SecurityUtils.getSubject(); 
				UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD); 
				try { 
					subject.login(token); 
				} catch (AuthenticationException e) { 
					throw new Exception(USERNAME+"身份验证失败");
				}
				}else{
					throw new Exception(USERNAME+"登录系统密码或用户名错误");
				}
	}

	public void index(PageData pageData,ModelAndView mv) {
		try{
			Session session = Jurisdiction.getSession();
			Admin user = (Admin)session.getAttribute(Const.SESSION_USER);				//读取session中的用户信息(单独用户信息)
			if (user != null) {
				Admin userr = (Admin)session.getAttribute(Const.SESSION_USERROL);		//读取session中的用户信息(含角色信息)
				if(null == userr){
					pageData.put("USER_ID", user.getUSER_ID());
					user = adminMapper.getUserAndRoleById(pageData);		//通过用户ID读取用户信息和角色信息
					session.setAttribute(Const.SESSION_USERROL, user);				//存入session	
				}else{
					user = userr;
				}
				String USERNAME = user.getUSERNAME();
				Role role = user.getRole();											//获取用户角色
				String roleRights = role!=null ? role.getRIGHTS() : "";				//角色权限(菜单权限)
				session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
				session.setAttribute(Const.SESSION_USERNAME, USERNAME);				//放入用户名到session
				List<Menu> allmenuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_allmenuList)){	
					allmenuList = menuService.listAllMenuQx("0");					//获取所有菜单
					if(EATools.notEmpty(roleRights)){
						allmenuList = this.readMenu(allmenuList, roleRights);		//根据角色权限获取本权限的菜单列表
					}
					session.setAttribute(USERNAME + Const.SESSION_allmenuList, allmenuList);//菜单权限放入session中
				}else{
					allmenuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_allmenuList);
				}
				List<Menu> menuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_menuList)){
					for(int i=0;i<allmenuList.size();i++){
						Menu menu = allmenuList.get(i);
							menuList.add(menu);
					}
					session.removeAttribute(USERNAME + Const.SESSION_menuList);
				}else{
					menuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_menuList);
				}
				if(null == session.getAttribute(USERNAME + Const.SESSION_QX)){
					session.setAttribute(USERNAME + Const.SESSION_QX, this.getUQX(USERNAME));	//按钮权限放到session中
				}
				mv.setViewName("index/index"); 
				mv.addObject("admin", user);
				mv.addObject("menuList", menuList);
				pageData.put("limit", 3);
				pageData.put("USERID", user.getUSER_ID());
				//mv.addObject("owerMsg", indexMapper.findOwerMsg(pageData));
			}else {
				mv.setViewName("login");//session失效后跳转登录页面
			}
			//mv.setViewName("index/index"); 
		} catch(Exception e){
			e.printStackTrace();
			mv.setViewName("login");
		}
	}
	
	/**获取用户权限
	 * @return
	 * {RIGHTS=536870854, CHA_QX=1, ADD_QX=1, ROLE_NAME=系统管理组, EDIT_QX=1, DEL_QX=1, PARENT_ID=0, ROLE_ID=1}
	 */
	public Map<String, String> getUQX(String USERNAME){
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			pd.put(Const.SESSION_USERNAME, USERNAME);
			pd = roleMapper.selectById(adminMapper.getUserInfo(pd).get("ROLE_ID").toString());										//获取角色信息														
			map.put("adds", pd.getString("ADD_QX"));	//增
			map.put("dels", pd.getString("DEL_QX"));	//删
			map.put("edits", pd.getString("EDIT_QX"));	//改
			map.put("chas", pd.getString("CHA_QX"));	//查
			List<PageData> buttonQXnamelist = new ArrayList<PageData>();
			if("admin".equals(USERNAME)){
				buttonQXnamelist = buttonMapper.listAll(pd);					//admin用户拥有所有按钮权限
			}else{
				buttonQXnamelist = buttonRightsMapper.listAllBrAndQxname(pd);	//此角色拥有的按钮权限标识列表
			}
			for(int i=0;i<buttonQXnamelist.size();i++){
				map.put(buttonQXnamelist.get(i).getString("QX_NAME"),"1");		//按钮权限
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<Menu> readMenu(List<Menu> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
			if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
				this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
			}else{
				menuList.remove(i);
				i--;
			}
		}
		return menuList;
	}
	/**
	 * 获取所有菜单并填充每个菜单的子菜单列表(系统菜单列表)(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listAllMenuQx(String MENU_ID) throws Exception {
		List<Menu> menuList = menuService.listSubMenuByParentId(MENU_ID);
		for(Menu menu : menuList){
			menu.setSubMenu(this.listAllMenuQx(menu.getMENU_ID()));
			menu.setTarget("treeFrame");
		}
		return menuList;
	}
	
	/**
	 * 用户列表
	 * @param pd
	 * @return
	 */
	public List<PageData> listUsers(Page page){
		return adminMapper.selectByPage(page);
	}
	/**
	 * 删除用户
	 * @param pd
	 * @return
	 */
	public void deleteU(PageData pd){
		adminMapper.delete(pd);
	}
	
	/**
	 * 保存用户
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public void saveU(PageData pd) throws Exception{
		
		adminMapper.insert(pd);
		
	}
	/**
	 * 批量删除
	 */
	public void deleteBatch(String[] arg){
		
		adminMapper.deleteBatch(arg);
		
	}
	
	/**
	 * 编辑用户
	 * @param pd
	 * @return
	 */
	public void editU(PageData pd){
		adminMapper.update(pd);
	}
	
	public PageData getUserInfo(PageData pd){
		return adminMapper.getUserInfo(pd);
	}
	
	public List<PageData> getUserByCondition(PageData pd){
		return adminMapper.getUserByCondition(pd);
	}
	
	
	public List<PageData> listAllUserByRoldId(PageData pd){
		return adminMapper.listAllUserByRoldId(pd);
	}
	
	
}
