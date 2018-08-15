package com.ythd.ower.common.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EAString {
	private static Log log = LogFactory.getLog(EAString.class);

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (StringUtils.isEmpty(mobiles)){
			return false;
		}else{
			return mobiles.matches(telRegex);
		}
	}
	/**
	 * 生产订单唯一编号
	 * @return
	 */
	public static String getFourSn() {
		return System.currentTimeMillis()+EAString.getRandomString(4);
	}
	/**
	 * 判断是否是空或者""字符串
	 * 
	 * @param value
	 *            : 判断的字符串
	 * @return boolean : 空串返回true,其它返回false
	 */
	public static boolean isNullStr(String value) {
		if (value == null) {
			return true;
		}
		if (value.trim().equals("") || value.trim().equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串转成数字
	 * 
	 * @param str
	 * @param def
	 * @return
	 */
	public static int stringToInt(String str, int def) {
		if (str == null) {
			return def;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			System.out.print(e.toString());
			return def;
		}
	}

	/**
	 * sha1加密
	 * 
	 * @param sourceString
	 * @return
	 */
	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] bytes = md.digest(resultString.getBytes());
			StringBuffer buf = new StringBuffer(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				if (((int) bytes[i] & 0xff) < 0x10) {
					buf.append("0");
				}
				buf.append(Long.toString((int) bytes[i] & 0xff, 16));
			}
			resultString = buf.toString().toUpperCase();
		} catch (Exception ex) {
		}
		return resultString;
	}

	/**
	 * 判断一个字符串是否
	 * 
	 * @param numberString
	 * @return
	 */
	public static Boolean isInt(String numberString) {
		if (numberString == null || numberString.trim().equals("")) {
			return false;
		}
		Pattern p = Pattern.compile("-*" + "\\d*");
		Matcher m = p.matcher(numberString);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 判断一个字符串是否是正整数
	 * 
	 * 
	 */
	public static Boolean isPositiveInt(String numberString) {

		if (numberString == null || numberString.trim().equals("")) {
			return false;
		}
		Pattern p = Pattern.compile("^\\d*[1-9]\\d*$");
		Matcher m = p.matcher(numberString);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等，
	 * 
	 * 随机范围1-9 a-z A-Z
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 返回指定位数且不重复的字符串
	 */
	public static String getRandomString(int length,boolean isNumber) {
		StringBuffer bu = new StringBuffer();
		String[] arr = null;
		if(isNumber){
		  String[] arr1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9","0"};
		  arr = arr1;
		}else{
			String[] arr1 = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
					"m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
					"I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
			arr = arr1;
		}
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(arr.length)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}

	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}
	
	/**
	 * 将int转换为指定35进制的字符串
	 * 
	 * @param num
	 * @return
	 */
	public static String convertTo35(int num) {
		String str = "", digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if (num == 0) {
			return "";
		} else {
			str = convertTo35(num / 35);
			return str + digit.charAt(num % 35);
		}
	}

	/**
	 * 字符串是否包含摸一个字符
	 * 
	 * @param all
	 *            字符串
	 * @param in
	 *            判断包含的字符
	 * @param reg
	 *            分割方法
	 * @return
	 */
	public static boolean isContain(String all, Object in, String reg) {
		boolean rs = false;
		if (all == null || in == null || reg == null) {
			return rs;
		}
		String inStr = in.toString();
		String[] strs = all.split(reg);
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(inStr)) {
				return true;
			}
		}
		return rs;
	}

	/**
	 * 替换字符串
	 * 
	 * @param inString
	 *            源字符串
	 * @param oldPattern
	 *            被替换的部分
	 * @param newPattern
	 *            替换后的结果部分
	 * @return
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		// Pick up error conditions
		if (inString == null)
			return null;
		if (oldPattern == null || newPattern == null)
			return inString;
		StringBuffer sbuf = new StringBuffer(); // Output StringBuffer we'll
												// build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf(oldPattern); // The index of an occurrence
													// we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos)); // Remember to append any
												// characters to the right of a
												// match
		return sbuf.toString();
	} // replace

	/**
	 * 删除指定字符，删除所有匹配项
	 * 
	 * @param inString
	 * @param pattern
	 * @return
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 截取字符串长度
	 * 
	 * @param inputString
	 * @param len
	 * @return
	 */
	public static String showString(String inputString, Integer len) {
		String str = "";
		try {
			if (inputString.length() > len) {
				str = inputString.substring(0, len) + "...";
			} else {
				str = inputString;
			}
		} catch (Exception e) {

		}
		return str;
	}

	/**
	 * 字符串不足多少位左侧补0
	 * 
	 * @param inputString
	 * @param d
	 *            位数
	 * @return
	 */
	public static String padLeft(String inputString, int d) {
		String str = inputString;
		while (str.length() < d) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 判断一个字符串是否由数字、字母、数字字母组成
	 * 
	 * @param pStr
	 *            需要判断的字符串
	 * @param pStyle
	 *            判断规则
	 * @return boolean 返回的布尔值
	 */
	public static boolean isTheStyle(String pStr, String pStyle) {
		for (int i = 0; i < pStr.length(); i++) {
			char c = pStr.charAt(i);
			if (pStyle.equals(EAConst.S_STYLE_N)) {
				if (!Character.isDigit(c))
					return false;
			} else if (pStyle.equals(EAConst.S_STYLE_L)) {
				if (!Character.isLetter(c))
					return false;
			} else if (pStyle.equals(EAConst.S_STYLE_NL)) {
				if (Character.isLetterOrDigit(c))
					return false;
			}
		}
		return true;
	}

	/**
	 * 获取6位无重复随机数
	 * 
	 * @return
	 */
	public static String getRandNumber() {
		StringBuffer strbufguess = new StringBuffer();
		String strguess = new String();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rannum = new Random();
		int count;
		int i = 0, temp_i = 0;
		for (int j = 10; j > 4; j--) {
			i = 0;
			temp_i = 0;
			count = rannum.nextInt(j);
			while (i <= count) {
				if (nums[temp_i] == -1)
					temp_i++;
				else {
					i++;
					temp_i++;
				}
			}
			strbufguess.append(Integer.toString(nums[temp_i - 1]));
			nums[temp_i - 1] = -1;
		}
		strguess = strbufguess.toString();
		rannum = null;
		strbufguess = null;
		nums = null;
		return strguess;
	}

	/**
	 * 检查是否有指定的CharSequence的实际文本。更具体地说，返回true ，如果字符串不 null ，其长度大于0，它至少包含一个非空格字符。
	 * null = false "" = false (" ") = false "12345" = true " 12345 " = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str) {
		if (str == null || str.equals("") || StringUtils.trim(str).length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 从空字符串对象获得空字符串
	 * 
	 * @param s
	 *            String 字符串对象
	 * @return String 空字符串
	 */
	public static String nullToEmpty(String s) {
		if (s == null) {
			return "";
		}
		if (s.equals("null")) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * 数组转字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	/**
	 * 格式化字符串
	 * 
	 * @param propertiesStr
	 *            String (format:{a=1,b=2,c=3})
	 * @return Properties
	 */
	public static Properties stringToProperties(String propertiesStr) {
		Properties pro = new Properties();
		if (propertiesStr != null && !propertiesStr.trim().equals("") && !propertiesStr.equals("{}")) {
			// delete '{' and '}' in the properties String:
			String str = delete(propertiesStr, "{");
			str = delete(str, "}");
			String tokens[] = commaDelimitedListToStringArray(str.trim());
			for (int i = 0; i < tokens.length; i++) {
				String property[] = delimitedListToStringArray(tokens[i].trim(), "=");
				if (property.length > 1) {
					pro.setProperty(property[0].trim(), property[1].trim());
				} else
					pro.setProperty(property[0].trim(), null);
			}
		}
		return pro;
	}

	/**
	 * 判断给定id字符串是否为大于1的数字，不是则返回0
	 * 
	 * @return
	 */
	public static int getIdIfIsId(Object obj) {
		int id = 0;
		if (obj == null || (obj != null && obj.equals("")))
			return id;
		String str = obj.toString();
		if (isInt(str))
			id = Integer.parseInt(str);
		if (id < 0)
			id = 0;
		return id;
	}

	/**
	 * 得到SN序列号
	 * 
	 * @return
	 */
	public static String getSnString() {
		String sn = System.currentTimeMillis() + "";
		sn = sn.substring(sn.length() - 7);
		return sn;
	}

	/**
	 * 判断俩个对象是否相同
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean equals(Object v1, Object v2) {
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 != null) {
			if (v2 == null) {
				return false;
			}
		}
		if (v2 != null) {
			if (v1 == null) {
				return false;
			}
		}
		if (v1 != null && v2 != null) {
			return v1.toString().equalsIgnoreCase(v2.toString());
		}
		return false;
	}

	/**
	 * @param chars
	 *            characters to delete e.g. az\n will delete as, zs and new
	 *            lines
	 */
	public static String deleteAny(String inString, String chars) {
		if (inString == null || chars == null)
			return inString;
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (chars.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}

	/**
	 * 计算一个字符串在指定字符串的出现次数
	 * 
	 * @param s
	 *            源字符串
	 * @param sub
	 *            查询字符串
	 * @return
	 */
	public static int countOccurrencesOf(String s, String sub) {
		if (s == null || sub == null || "".equals(sub))
			return 0;
		int count = 0, pos = 0, idx = 0;
		while ((idx = s.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	// ==================================================================================
	// 将字符串转为队列
	// ==================================================================================
	/**
	 * 字符串分割为字符串数组
	 * 
	 * @param s
	 * @param delimiter
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static String[] delimitedListToStringArray(String s, String delimiter) {
		if (s == null)
			return new String[0];
		if (delimiter == null) {
			return new String[] { s };
		}
		List l = new LinkedList();
		int delimCount = 0;
		int pos = 0;
		int delpos = 0;
		while ((delpos = s.indexOf(delimiter, pos)) != -1) {
			l.add(s.substring(pos, delpos));
			pos = delpos + delimiter.length();
		}
		if (pos <= s.length()) {
			// Add rest of String
			l.add(s.substring(pos));
		}
		return (String[]) l.toArray(new String[l.size()]);
	}

	/**
	 * 字符串转为字符串数组，以逗号分割的字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String[] commaDelimitedListToStringArray(String s) {
		return delimitedListToStringArray(s, ",");
	}

	/**
	 * 字符串转为TreeSet，以逗号分割的字符串
	 * 
	 * @param s
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set commaDelimitedListToSet(String s) {
		Set set = new TreeSet();
		String[] tokens = commaDelimitedListToStringArray(s);
		for (int i = 0; i < tokens.length; i++)
			set.add(tokens[i]);
		return set;
	}

	// ==================================================================================
	// 将队列转为字符串
	// ==================================================================================
	/**
	 * 将字符串List转为字符串，以逗号分割
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String list2String(List list) {
		if (list.size() > 0) {
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < list.size() - 1; i++) {
				str.append((String) list.get(i));
				str.append(",");
			}
			str.append((String) list.get(list.size() - 1));
			return str.toString();
		} else
			return "";
	}

	/**
	 * 将数组转为字符串
	 * 
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null)
			return "null";
		else {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(delim);
				sb.append(arr[i]);
			}
			return sb.toString();
		}
	}

	/**
	 * 将collection转为字符串
	 * 
	 * @param c
	 * @param delim
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String collectionToDelimitedString(Collection c, String delim) {
		if (c == null)
			return "null";
		return iteratorToDelimitedString(c.iterator(), delim);
	}

	/**
	 * 将Iterator转为字符串
	 * 
	 * @param itr
	 * @param delim
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String iteratorToDelimitedString(Iterator itr, String delim) {
		if (itr == null)
			return "null";
		else {
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (itr.hasNext()) {
				if (i++ > 0)
					sb.append(delim);
				sb.append(itr.next());
			}
			return sb.toString();
		}
	}

	// ======================================================================================
	//////////////////////////// 字符串转码////////////////////////////////////////////////////////
	// ======================================================================================
	/**
	 * 将GB2312转换为utf-8
	 * 
	 * @param strIn
	 *            String
	 * @return String
	 */
	public static String GB2312ToUTF8(String strIn) {
		String strOut = "";
		if (strIn == null)
			return "";
		try {
			byte[] b = strIn.getBytes("GB2312");
			// strOut = new String(b, "GB2312");//转为GB2312
			strOut = new String(b, "utf-8"); // 转为UTF-8
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 将utf-8转换为GB2312
	 * 
	 * @param strIn
	 *            String
	 * @return String
	 */
	public static String UTF8ToGB2312(String strIn) {
		String strOut = "";
		if (strIn == null)
			return "";
		try {
			byte[] b = strIn.getBytes("utf-8");
			// strOut = new String(b, "GB2312");//转为GB2312
			strOut = new String(b, "GB2312"); // 转为GB2312
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 将ISO8859_1转换为utf-8
	 * 
	 * @param strIn
	 *            String
	 * @return String
	 */
	public static String UnicodeToUTF8(String strIn) {
		String strOut = "";
		if (strIn == null)
			return "";
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			// strOut = new String(b, "GB2312");//转为GB2312
			strOut = new String(b, "utf-8"); // 转为UTF-8
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 编码
	 * 
	 * @return String
	 */
	public static String base64Encode(String str) {
		byte[] bstr = str.getBytes();
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static String base64Decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
			return new String(bt, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将double转成百分数形式
	 * 
	 * @param target
	 * @return
	 */
	public static String numberBFSFormat(double target) {
		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(3);
		num.setMaximumFractionDigits(2);
		return num.format(target);
	}
	/**
	 * 验证手机号码
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber){
		boolean flag = false;
		try{
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
}