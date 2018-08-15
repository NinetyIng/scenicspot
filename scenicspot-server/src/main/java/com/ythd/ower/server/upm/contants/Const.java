package com.ythd.ower.server.upm.contants;
public class Const {
	
	public static final String APP_LAYOUT_DESKTOP = "2";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(main/index)|(wx)|(appwx)|(doLogin)|(logout)|(statics)|(assets)|(uploadFiles)|(healthCheck)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String SESSION_USER = "sessionUser"; // session用的用户
	public static final String SESSION_ERCODE = "sessionErcode"; // session用的用户
	public static final String SESSION_allmenuList = "allmenuList"; // 全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_USERNAME = "USERNAME"; // 用户名
	public static final String LOGIN = "/mian/login"; // 登录地址
	public static final String SESSION_USERROL = "USERROL"; // 用户对象
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList"; // 当前菜单
	public static final String SESSION_userpds = "userpds";
}
