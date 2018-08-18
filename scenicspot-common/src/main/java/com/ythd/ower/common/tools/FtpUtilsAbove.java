package com.ythd.ower.common.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.properties.PropertiesHelper;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * 高版本的ftp上传控件
 * @author liujunbo
 *
 */

public class FtpUtilsAbove {

	private FTPClient ftpClient;
	private Ftp ftpPropery = new Ftp();
	public FtpUtilsAbove(){

		ftpPropery.setIpAddr(ConfigureManager.getSystemConfig().getFtpConfigure().getIp());
		ftpPropery.setPwd(ConfigureManager.getSystemConfig().getFtpConfigure().getPassword());
		ftpPropery.setPort(ConfigureManager.getSystemConfig().getFtpConfigure().getPort());
		ftpPropery.setUserName(ConfigureManager.getSystemConfig().getFtpConfigure().getUsername());
	}
	
	/**
	 * 关闭ftp连接
	 */
	private  void closeFtp() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String upload(MultipartFile file, String projectName, String modelName) throws IOException{
		if (file.getBytes().length == 0 && file.getSize() == 0
				&& file.getOriginalFilename().equals(StringUtils.EMPTY)) {
			return StringUtils.EMPTY;
		}
		ftpClient = new FTPClient();
		ftpClient.setBufferSize(5024*1024);
		try {
			ftpClient.connect(ftpPropery.getIpAddr(), ftpPropery.getPort());
			StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			ftpClient.login(ftpPropery.getUserName(), ftpPropery.getPwd());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置操作的文件类型
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return StringUtils.EMPTY;
			}
			ftpClient.changeWorkingDirectory(ftpPropery.getPath());
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			for (String directoryName : directoryNames) {
				ftpClient.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				ftpClient.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			String fileName = System.currentTimeMillis()+EAString.getRandomString(7)+".png";
			BufferedInputStream input  = new BufferedInputStream(file.getInputStream());
			ftpClient.storeFile(fileName,input); 
			input.close();
			closeFtp();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(fileName).toString();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeFtp();
		}
		return "";
	}
	
	public String upload(BufferedInputStream stream, String projectName, String modelName) throws IOException{
		ftpClient = new FTPClient();
		ftpClient.setBufferSize(5024*1024);
		try {
			ftpClient.connect(ftpPropery.getIpAddr(), ftpPropery.getPort());
			StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			ftpClient.login(ftpPropery.getUserName(), ftpPropery.getPwd());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置操作的文件类型
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return "";
			}
			ftpClient.changeWorkingDirectory(ftpPropery.getPath());
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			for (String directoryName : directoryNames) {
				ftpClient.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				ftpClient.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			String fileName = System.currentTimeMillis()+EAString.getRandomString(7)+".png";
			ftpClient.storeFile(fileName,stream);
			stream.close();
			closeFtp();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(fileName).toString();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeFtp();
		}
		return "";
	}
	
	/**
	 * 上传音频
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public String[] uploadVoice(MultipartFile file, String projectName, String modelName) throws IOException {
		String [] result = new String[2]; //数组第一个元素存储视频地址  第二个元素存储视频截图地址
		if (file.getBytes().length == 0 && file.getSize() == 0 && file.getOriginalFilename().equals("")) {
			return null;
		}
		ftpClient = new FTPClient();
		ftpClient.setBufferSize(5024*1024);
		try {
			ftpClient.connect(ftpPropery.getIpAddr(), ftpPropery.getPort());
			StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			ftpClient.login(ftpPropery.getUserName(), ftpPropery.getPwd());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置操作的文件类型
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return null;
			}
			ftpClient.changeWorkingDirectory(ftpPropery.getPath());
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			for (String directoryName : directoryNames) {
				ftpClient.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				ftpClient.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			String fileName = System.currentTimeMillis()+EAString.getRandomString(7)+".mp3";
			BufferedInputStream input  = new BufferedInputStream(file.getInputStream());
			ftpClient.storeFile(fileName,input); 
			input.close();
			closeFtp();
			result[0] = savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(fileName).toString();
			
			String osName = System.getProperties().getProperty("os.name");
			System.out.println("当前程序运行在《》《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《"+osName);
			if(osName.toLowerCase().indexOf("windows") != -1){
				result[1] = getVideoTime(file)+"";
			}else{
				result[1] = getVideoTimeForLinux(file)+"";
			}
			return result;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeFtp();
		}
		return null;
	}
	
	
	public String[] uploadVedio(MultipartFile file, String projectName, String modelName) throws Exception{
		String [] result = new String[3]; //数组第一个元素存储视频地址  第二个元素存储视频截图地址
		if (file.getBytes().length == 0 && file.getSize() == 0 && file.getOriginalFilename().equals("")) {
			return null;
		}
		ftpClient = new FTPClient();
		ftpClient.setBufferSize(5024*1024);
		try {
			ftpClient.connect(ftpPropery.getIpAddr(), ftpPropery.getPort());
			StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			ftpClient.login(ftpPropery.getUserName(), ftpPropery.getPwd());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置操作的文件类型
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return null;
			}
			ftpClient.changeWorkingDirectory(ftpPropery.getPath());
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			for (String directoryName : directoryNames) {
				ftpClient.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				ftpClient.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			String fileName = System.currentTimeMillis()+EAString.getRandomString(7)+".mp4";
			BufferedInputStream input  = new BufferedInputStream(file.getInputStream());
			ftpClient.storeFile(fileName,input); 
			input.close();
			closeFtp();
			result[0] = savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(fileName).toString();
			result[1] = storeZImg(file);
			
			String osName = System.getProperties().getProperty("os.name");
			System.out.println("当前程序运行在《》《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《"+osName);
			if(osName.toLowerCase().indexOf("windows") != -1){
				result[2] = getVideoTime(file)+"";
			}else{
				result[2] = getVideoTimeForLinux(file)+"";
			}
			return result;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeFtp();
		}
		return null;
	}
	/**
	 * 存储视频一帧图片到本地
	 * @return
	 * @throws Exception 
	 */
	private  String  storeZImg(MultipartFile file) throws Exception{
		/**
		 * 获取视频文件的帧截图
		 */
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
		System.out.println("上传文件路径<><><>><><<>><><><><><<>><><><>"+f.getAbsolutePath());
		
		System.out.println("文件名字<><<>><<>><><><<>><<><>><<>"+f.getName());
		System.out.println("文件完整路径名字<><<>><<>><><><<>><<><>><<>"+f.getAbsolutePath()+f.getName());
		String parentPath = f.getAbsolutePath();
		parentPath = parentPath.substring(0,parentPath.lastIndexOf(File.separatorChar));
		/*System.out.println(parentPath);
		System.out.println("<><><><>><><><><<>><"+f.getAbsolutePath());
		File newFile = new File(
				f.getPath().substring(0, f.getPath().lastIndexOf("\\") + 1) + System.currentTimeMillis()+EAString.getRandomString(7) + "."
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
		// 获得输入流,并通过输出流将文件写入临时文件夹
		InputStream is = file.getInputStream();
		OutputStream os = new FileOutputStream(newFile);
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = is.read(b)) != -1) {
			os.write(b, 0, len);
		}
		os.close();
		is.close();
		newFile.delete();*/
		File imgFile = new File(parentPath+File.separatorChar+"test.jpg");
		if(!imgFile.exists()){
			imgFile.createNewFile();
		}
		/**
		 * 获取视频文件的帧截图结束
		 */
		//判断运行的系统
		String osName = System.getProperties().getProperty("os.name");
		System.out.println("当前程序运行在《》《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《"+osName);
		String result = "";
		if(osName.toLowerCase().indexOf("windows") != -1){
			//说明是linux系统
			result =  take(f.getAbsolutePath(), imgFile);
		}else{
			//是window系统
			result =  takeLiunx(f.getAbsolutePath(), imgFile);
		}
		
		if(imgFile.exists()){
			imgFile.delete();
		}
		return result;
	}
	public  String take(String videoLocation, File imgFile)
	{
		System.out.println("视频截图零食存放地址<>><<>><><><"+imgFile.getAbsolutePath());
	    // 低精度
		List<String> commend = new ArrayList<String>();
		commend.add(File.separatorChar+"ffmpeg.exe");//视频提取工具的位置
		commend.add("-i");
		commend.add(videoLocation);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("08.010");
		commend.add("-t");
		commend.add("0.001");
		commend.add("-s");
		commend.add("750x322");
		commend.add(imgFile.getAbsolutePath());
		try {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(commend);
		builder.start();
		System.out.println("截图文件大小<><><>><<><>><<>><<><>><><"+imgFile);
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(imgFile));
		//存储截图文件
		String reslutPath =  upload(input, "wzd", "screen");
		input.close();
		imgFile.delete();
		return reslutPath;
	} catch (Exception e) {
	e.printStackTrace();
	return "";
	}
	} //ffmpeg -i     -y -f image2 -ss 00:00:06 -t 00:00:01 -s 750x322
	//linux服务器视频截图
	public  String takeLiunx(String videoLocation, File imgFile) throws Exception { 
		 String command = "ffmpeg -i " + videoLocation + " -y -f image2 -ss 00:00:06 -t 00:00:01 -s 750x322 " + imgFile.getAbsolutePath(); 
		 try { 
			 Runtime rt = Runtime.getRuntime();
			 Process proc = rt.exec(command);
			 InputStream stderr = proc.getErrorStream();
			 InputStreamReader isr = new InputStreamReader(stderr);
			 BufferedReader br = new BufferedReader(isr);
			 String line = null;
			 while ((line = br.readLine()) != null)
			 System.out.println("执行返回的信息流<>><><<><#############################################>><><><<><><>"+line);
		  } catch (Throwable t) { 
		 t.printStackTrace(); 
		 return ""; 
		  } 
		 BufferedInputStream input = new BufferedInputStream(new FileInputStream(imgFile));
			//存储截图文件
		 String reslutPath =  upload(input, "wzd", "screen");
		 imgFile.delete();
		 input.close();
		 return reslutPath; 
	 }
	
	/**
	 * 获取音频视频的时长
	 * @return
	 * @throws IOException 
	 */
	public  int getVideoTime(MultipartFile file) throws IOException {  
		
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
        List<String> commands = new java.util.ArrayList<String>();  
        String  video_path = f.getAbsolutePath();
        commands.add("/ffmpeg.exe");  
        commands.add("-i");  
        commands.add(f.getAbsolutePath());  
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commands);  
            final Process p = builder.start();  
            //从输入流中读取视频信息  
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
            StringBuffer sb = new StringBuffer();  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                sb.append(line);  
            }  
            br.close();  
            //从视频信息中解析时长  
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";  
            Pattern pattern = Pattern.compile(regexDuration);  
            Matcher m = pattern.matcher(sb.toString());  
            if (m.find()) {  
                int time = getTimelen(m.group(1));  
                System.out.println(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");  
                return time;  
            } 
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
		}  
        return 0;  
    }  
	
	
	/**
	 * 获取音频视频的时长
	 * @return
	 * @throws IOException 
	 */
	public  int getVideoTimeForLinux(MultipartFile file) throws IOException {  
		
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
        String  video_path = f.getAbsolutePath();
        String command = "ffmpeg -i " + video_path; 
		 try { 
		 Runtime rt = Runtime.getRuntime(); 
		 Process proc = rt.exec(command); 
		 InputStream stderr = proc.getErrorStream(); 
		 InputStreamReader isr = new InputStreamReader(stderr); 
		 BufferedReader br = new BufferedReader(isr); 
		 String line = "";
		 StringBuffer sb = new StringBuffer();  
		 while ((line = br.readLine()) != null){
			 System.out.println("执行返回的信息流<>><><<><#############################################>><><><<><><>"+line);
			 sb.append(line);  
		 }
            br.close();  
            //从视频信息中解析时长  
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";  
            Pattern pattern = Pattern.compile(regexDuration);  
            Matcher m = pattern.matcher(sb.toString());  
            if (m.find()) {  
                int time = getTimelen(m.group(1));  
                System.out.println(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");  
                return time;  
            } 
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
		}  
        return 0;  
    }  
	
	
	
	 private static int getTimelen(String timelen){  
	        int min=0;  
	        String strs[] = timelen.split(":");  
	        if (strs[0].compareTo("0") > 0) {  
	            min+=Integer.valueOf(strs[0])*60*60;//秒  
	        }  
	        if(strs[1].compareTo("0")>0){  
	            min+=Integer.valueOf(strs[1])*60;  
	        }  
	        if(strs[2].compareTo("0")>0){  
	            min+=Math.round(Float.valueOf(strs[2]));  
	        }  
	        return min;  
	    }  
	
}
