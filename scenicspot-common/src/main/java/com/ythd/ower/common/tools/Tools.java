package com.ythd.ower.common.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.ythd.ower.common.dto.PageData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 说明：常用工具
 * 创建人：FH Q371855779
 * 修改时间：2015年11月24日
 * @version
 */
public class Tools {
	
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照yyyy-MM-dd的格式，字符串转日期
	 * @return
	 */
	public static String str2YMD(String dateStr){
		if(notEmpty(dateStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date=sdf.parse(dateStr);
				dateStr.toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date().toString();
		}else{
			return new Date().toString();
		}
	}
	
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 public static boolean isMoney(String str)
	    { 
	        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
	        java.util.regex.Matcher match=pattern.matcher(str); 
	        if(match.matches()==false) 
	        { 
	           return false; 
	        } 
	        else 
	        { 
	           return true; 
	        } 
	    }
	 /**
		 * 检测KEY是否正确
		 * @param paraname  传入参数
		 * @return 为空则返回true，不否则返回false
		 */
		public static boolean checkKey(String paraname, String token){
			paraname = (null == paraname)? "":paraname;
			token = token == null ? "" : token;
			return MD5.md5(paraname).equalsIgnoreCase(token);
		}
	 
	/**
	 * 读取txt里的单行内容
	 */
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	/**
	 * 
	 * 获取邀请码
	 */
	public static String getRegisterCode(int num,String type) {
		Random random = new Random(); 
		String randString = "";
		if("0".equals(type)){
			if(num>9){
				num = 9;
			}
			randString = "0123456789";
		}else if("1".equals(type)){
			randString = "abcdefghijklmnopqrstuvwxyz";
		}else if("2".equals(type)){
			randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}else if("3".equals(type)){
			randString = "0123456789abcdefghijklmnopqrstuvwxyz";
		}else{
//			randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			randString = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		}
		String RegisterCode = "";
		while (RegisterCode.length()<num) {
			String rand = String.valueOf(getRandomString(random.nextInt(randString 
					.length()),randString));
			if(!RegisterCode.contains(rand)){
				RegisterCode+=rand;
			}
		}
		return RegisterCode;
	}
	
	
	/**
	 * 获取随机的字符
	 */
    public static String getRandomString(int num,String s) { 
        return String.valueOf(s.charAt(num)); 
    } 
    
	public static void main(String[] args) {
		System.out.println(isMoney("2.000"));
	}
	
	/**
	 * 将PageData里面的值为空格的替换成null
	 */
	public static void replaceEmpty(PageData pd){
		Iterator entries = pd.entrySet().iterator(); 
		Map.Entry entry; 
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			Object valueObj = entry.getValue(); 
			if(valueObj == null){
				continue;
			}else{
				if(valueObj.toString().trim().equals("")){
					pd.put(entry.getKey(), null);
				}
				if(valueObj.toString().equals("-1")){
					pd.put(entry.getKey(), null);
				}
			}
		}
	}
	
	public static MultipartFileParam parse(HttpServletRequest request) throws Exception {
        MultipartFileParam param = new MultipartFileParam();

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        param.setMultipart(isMultipart);
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 得到所有的表单域，它们目前都被当作FileItem
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                System.out.println("field name has:"+fileItem.getFieldName());
                if (!"file".equals(fileItem.getFieldName())){
                    System.out.println("field val has:"+fileItem.getString());
                }

                if (fileItem.getFieldName().equals("id")) {
                    param.setId(fileItem.getString());
                } else if (fileItem.getFieldName().equals("name")) {
                    param.setFileName(new String(fileItem.getString().getBytes(
                            "ISO-8859-1"), "UTF-8"));
                } else if (fileItem.getFieldName().equals("chunks")) {
                    param.setChunks(NumberUtils.toInt(fileItem.getString()));
                } else if (fileItem.getFieldName().equals("chunk")) {
                    param.setChunk(NumberUtils.toInt(fileItem.getString()));
                } else if (fileItem.getFieldName().equals("file")) {
                    param.setFileItem(fileItem);
                    param.setSize(fileItem.getSize());
                } else{
                    param.getParam().put(fileItem.getFieldName(), fileItem.getString());
                }
            }
        }

        return param;
    }
}
