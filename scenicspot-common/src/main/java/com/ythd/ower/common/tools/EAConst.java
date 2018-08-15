package com.ythd.ower.common.tools;

/**
 * 常量�?
 * 
 * @author Leon
 * @since 2016-07-13
 */
public class EAConst {

	/**
	 * 字符串组成类�?br>
	 * number:数字字符�?
	 */
	public static final String S_STYLE_N = "number";

	/**
	 * 字符串组成类�?br>
	 * letter:字母字符�?
	 */
	public static final String S_STYLE_L = "letter";

	/**
	 * 字符串组成类�?br>
	 * numberletter:数字字母混合字符�?
	 */
	public static final String S_STYLE_NL = "numberletter";

	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_Date2 = "yyyyMMdd";
	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_Date3 = "MM/dd/yyyy";
	/**
	 * 格式�?12小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime_12 = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 格式�?br>
	 * FORMAT_DateTime: 日期
	 */
	public static final String FORMAT_Date = "yyyy-MM-dd";

	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time = "HH:mm:ss";
	
	/**
	 * 格式�?12小时�?<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time_12 = "hh:mm:ss";

	/**
	 * 换行�?br>
	 * \n:换行
	 */
	public static final String ENTER = "\n";

	/**
	 * 异常信息统一头信�?br>
	 * 非常遗憾的�?知您,程序发生了异�?
	 */
	public static final String Exception_Head = "\nOH,MY GOD! SOME ERRORS OCCURED! " + "AS FOLLOWS.\n";

	/**
	 * Ext表格加载模式<br>
	 * \n:非翻页排序加载模�?
	 */
	public static final String EXT_GRID_FIRSTLOAD = "first";

	/**
	 * Excel模板数据类型<br>
	 * number:数字类型
	 */
	public static final String ExcelTPL_DataType_Number = "number";

	/**
	 * Excel模板数据类型<br>
	 * number:文本类型
	 */
	public static final String ExcelTPL_DataType_Label = "label";

	/**
	 * HTTP请求类型<br>
	 * 1:裸请�?
	 */
	public static final String PostType_Nude = "1";

	/**
	 * HTTP请求类型<br>
	 * 0:常规请求
	 */
	public static final String PostType_Normal = "0";

	/**
	 * Ajax请求超时错误�?br>
	 * 999:Ajax请求超时错误�?
	 */
	public static final int Ajax_Timeout = 999;
	
	/**
	 * Ajax请求非法错误�?br>
	 * 998:当前会话userid和登录时候的userid不一�?会话被覆�?
	 */
	public static final int Ajax_Session_Unavaliable = 998;
	
	/**
	 * Ajax请求非法错误�?br>
	 * 997:未知请求异常
	 */
	public static final int Ajax_Unknow = 997;
	
	/**
	 * 交易状�?:成功
	 */
	public static final Boolean TRUE = new Boolean(true);
	
	/**
	 * 交易状�?:失败
	 */
	public static final Boolean FALSE = new Boolean(false);
	
	/**
	 * 交易状�?:成功
	 */
	public static final String SUCCESS = "1";
	
	/**
	 * 交易状�?:失败
	 */
	public static final String FAILURE = "0";

	/**
	 * 分页查询分页参数缺失错误信息
	 */
	public static final String ERR_MSG_QUERYFORPAGE_STRING = "您正在使用分页查�?但是你传递的分页参数缺失!如果不需要分页操�?您可以尝试使用普通查�?queryForList()方法";
	
	/**
	 * Flash图标色彩数组
	 */
	public static String[] CHART_COLORS = {"AFD8F8","F6BD0F","8BBA00","008E8E","D64646","8E468E","588526","B3AA00","008ED6","9D080D","A186BE","1EBE38"};

	/**
	 * Flash图表双Y�?
	 * p:主轴
	 */
	public static final String REPORT2Y_FIRST = "P";
	
	/**
	 * Flash图表双Y�?
	 * S:次轴
	 */
	public static final String REPORT2Y_SECOND = "S";
	
	/**
	 * 应用布局模式:传统经典布局
	 */
	public static final String APP_LAYOUT_CLASSIC = "1";
	
	/**
	 * 应用布局模式:个�?桌面布局
	 */
	public static final String APP_LAYOUT_DESKTOP = "2";
	public static final String NO_INTERCEPTOR_PATH = ".*/((appwx)|(wx)|(login)|(checkLogin)|(logout)|(code)|(app)|(weixin)|(statics)|(plugins)|(main)|(websocket)|(ueditor)|(competition)|(jjresources)|(desc)|(assets)).*";	//不对匹配该值的访问路径拦截（正则）
}