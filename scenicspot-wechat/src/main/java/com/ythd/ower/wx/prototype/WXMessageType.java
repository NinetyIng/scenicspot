package com.ythd.ower.wx.prototype;

public class WXMessageType {
	/**
	 * 文本消息
	 */
	public static String TEXT="text";
	/**
	 * 图片消息
	 */
	public static String IMAGE="image";
	/**
	 * 图文消息
	 */
	public static String NEWS="news";
	/**
	 * 位置消息
	 */
	public static String LOCATION="location";
	/**
	 * 链接消息
	 */
	public static String LINK="link";
	/**
	 * 事件消息
	 */
	public static String EVENT="event";
	/**
	 * 事件消息  , 扫描二维码
	 */
	public static String EVENT_SCAN="scan";
	/**
	 * 事件消息 , 添加订阅
	 */
	public static String EVENT_SUBSCRIBE="subscribe";
	/**
	 *  事件消息 , 取消订阅
	 */
	public static String EVENT_UNSUBSCRIBE="unsubscribe";
	/**
	 * 事件消息 , 自定义菜单单击
	 */
	public static String EVENT_CLICK="click";
	/**
	 * 事件消息  , 自定义菜单单击，点击打开链接
	 */
	public static String EVENT_VIEW="view";
	
}
