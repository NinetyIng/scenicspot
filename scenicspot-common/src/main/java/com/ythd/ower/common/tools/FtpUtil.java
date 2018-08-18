package com.ythd.ower.common.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.properties.PropertiesHelper;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FtpUtil {

	private static FTPClient ftp;
	private static Ftp ftpPropery = new Ftp();
	static {
		try {
			ftpPropery.setIpAddr(ConfigureManager.getSystemConfig().getFtpConfigure().getIp());
			ftpPropery.setPwd(ConfigureManager.getSystemConfig().getFtpConfigure().getPassword());
			ftpPropery.setPort(ConfigureManager.getSystemConfig().getFtpConfigure().getPort());
			ftpPropery.setUserName(ConfigureManager.getSystemConfig().getFtpConfigure().getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 连接FTP服务器
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static boolean connectFtp(Ftp f) throws Exception {
		boolean flag = false;
		try {
			ftp = new FTPClient();
			ftp.setBufferSize(1024*1024);
			int reply;
			if (f.getPort() == null) {
				ftp.connect(f.getIpAddr(), 21);
			} else {
				ftp.connect(f.getIpAddr(), f.getPort());
			}
			ftp.login(f.getUserName(), f.getPwd());
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置操作的文件类型
			reply = ftp.getReplyCode(); // 返回ftp服务器响应码
			if (!FTPReply.isPositiveCompletion(reply)) { // 判断是否是一个积极的相应
				ftp.disconnect();
				return flag;
			}
			ftp.changeWorkingDirectory(f.getPath());
			flag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception();
		}
		return flag;
	}

	/**
	 * 关闭ftp连接
	 */
	private static void closeFtp() {
		if (ftp != null && ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ftp上传方法
	 * 
	 * @param f
	 * @throws Exception
	 */
	
	private static  void upload(File f, String... directoryNames) throws Exception {
		try {
			connectFtp(ftpPropery);
			// 创建文件夹
			for (String directoryName : directoryNames) {
				ftp.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				System.out.println(ftp.getReplyStrings());
				System.out.println(ftp.getDataConnectionMode());
				System.out.println(ftp.getReplyCode());
				
				ftp.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			// 判断该file对象是文件夹还是文件
			if (f.isDirectory()) {
				ftp.makeDirectory(f.getName()); // 在ftp服务器上创建该文件夹
				ftp.changeWorkingDirectory(f.getName());//在当前ftp回话上改变当前工作目录
				String[] files = f.list(); // 得到该文件夹下所有的文件夹名称和文件名称
				for (String fstr : files) {
					File file1 = new File(f.getPath() + "/" + fstr); // 根据文件或文件夹名称构建file对象。
					// 判断该file对象是文件夹还是文件
					if (file1.isDirectory()) {
						upload(file1);
						ftp.changeToParentDirectory();
					} else {
						File file2 = new File(f.getPath() + "/" + fstr);
						FileInputStream input = new FileInputStream(file2); // 根据file对象创建字节输入流
						ftp.storeFile(file2.getName(), input); // 根据输入流在ftp服务器上创建指定名称的文件
						input.close();
					}
				}
			} else {
				File file2 = new File(f.getPath());
				/**
				 * 转成buffer 效率更高
				 */
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(file2));
				ftp.storeFile(file2.getName(), input);
				input.close();
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			closeFtp();
		}
	}
	/**
	 * 上传文件至FTP服务器
	 * 
	 * @param file
	 *            springMVC上传文件的MultipartFile对象
	 *            项目名称
	 *            模块名称
	 * @return 最终保存后的路径
	 * @throws IOException
	 * @throws Exception
	 */
	public static String upload(MultipartFile file, Map<String,String> param/*,String projectName, String modelName*/)
			throws IOException, Exception {
		//initFtp(param);
		
		String projectName = param.get("projectName");
		String modelName = param.get("modelName");
		if (file.getBytes().length == 0 && file.getSize() == 0 && file.getOriginalFilename().equals("")) {
			return "";
		}
		StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件夹
		try {
			// 将spring的MultipartFile对象转换成file对象
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();
			File newFile = new File(
					f.getPath().substring(0, f.getPath().lastIndexOf("\\") + 1) + System.currentTimeMillis() + "."
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

			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };

			// 调用上传文件方法
			FtpUtil.upload(newFile, directoryNames);

			// 删除临时文件夹
			newFile.delete();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(newFile.getName()).toString();
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
	/**
	 * 上传文件至FTP服务器
	 * 
	 * @param file
	 *            springMVC上传文件的MultipartFile对象
	 * @param projectName
	 *            项目名称
	 * @param modelName
	 *            模块名称
	 * @return 最终保存后的路径
	 * @throws IOException
	 * @throws Exception
	 */
	public static synchronized String upload(MultipartFile file, String projectName, String modelName)
			throws IOException, Exception {
		if (file.getBytes().length == 0 && file.getSize() == 0 && file.getOriginalFilename().equals("")) {
			return "";
		}
		StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
		try {
			// 将spring的MultipartFile对象转换成file对象
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();
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

			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };

			// 调用上传文件方法
			FtpUtil.upload(newFile, directoryNames);

			// 删除临时文件夹
			newFile.delete();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(newFile.getName()).toString();
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
	public static String upload(File file, String projectName, String modelName)
			throws IOException, Exception {
		if(file==null || file.length()<=0){
			return "";
		}
		StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
		try {
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			// 调用上传文件方法
			FtpUtil.upload(file, directoryNames);
			// 删除临时文件夹
			file.delete();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(file.getName()).toString();
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
	/**
	 * 文件字节数组直接上传到FTP
	 * @param bt
	 * @param projectName
	 * @param modelName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String upload(byte[] bt, String projectName, String modelName,String fileName) throws Exception {
		try{
			connectFtp(ftpPropery);
			StringBuffer savePath = new StringBuffer(); // 文件最终保存的文件路径
			// 获得当前时间参数
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String[] directoryNames = { projectName, modelName, String.valueOf(year), String.valueOf(month),
					String.valueOf(day) };
			// 创建文件夹
			for (String directoryName : directoryNames) {
				ftp.makeDirectory(directoryName); // 在ftp服务器上创建该文件夹
				ftp.changeWorkingDirectory(directoryName); // 在当前ftp回话上改变当前工作目录
			}
			
			InputStream input = new ByteArrayInputStream(bt);
			ftp.storeFile(fileName, input);
/*			ftp.storeFile(fileName.substring(fileName.indexOf("."), fileName.length()-1), input);*/
			input.close();
			return savePath.append("/")
					.append(projectName).append("/").append(modelName).append("/").append(year).append("/")
					.append(month).append("/").append(day).append("/").append(fileName).toString();
		} catch (Exception e) {
			throw e;
		} finally {
			closeFtp();
		}
	}

	public static InputStream getFileInputStreamByPath(String path) throws Exception {
		try {
			connectFtp(ftpPropery);
			return ftp.retrieveFileStream(path);
		} catch (Exception e) {
			throw e;
		} finally {
			closeFtp();
		}
	}


	
    public static File tofile(byte[] bfile, String filePath,String fileName) {  
        File file = null;  
        try {
        	file = new File(filePath+"\\"+fileName);  
            OutputStream os = new FileOutputStream(file);                       
            BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(bfile));
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
                os.flush();
            }
            is.close();
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return file;
    }
	
	 /** 
     * 根据byte数组，生成文件 
     */  
    public static File getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
            return file;
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return null;
    }
    
    public  static FTPFile[] fileList(String filePath) throws Exception{
    	FTPFile[] result = null;
    	connectFtp(ftpPropery);
    	if(StringUtils.isNotEmpty(filePath)){
    		result = ftp.listFiles(filePath);
    	}else{
    		result = ftp.listFiles();
    	}
    	closeFtp();
    	return result;
    }
    /**
     * 递归列出所有的文件
     */
    public static List<FileEntity> recursionFileList(String filePath) throws IOException{
    	
    	List<FileEntity> storeFileEntitys = ftpFileTransFileEntity(filePath);
    	for(FileEntity tempEntity : storeFileEntitys){
    		if(!tempEntity.isFile()){
    			tempEntity.setFilePath(filePath+tempEntity.getFileName()+"/");
    			List<FileEntity> childs = recursionFileList(tempEntity.getFilePath());
    			tempEntity.setFileEntitys(childs);
    		}else{
    			tempEntity.setFilePath(filePath+tempEntity.getFileName());
    		}
    	}
    	return storeFileEntitys;
    } 
    public  static List<FileEntity>  recursionFileEntity(String filePath) throws Exception{
    	connectFtp(ftpPropery);
    	List<FileEntity> results =  recursionFileList(filePath);
    	closeFtp();
    	return results;
    }
    public static List<FileEntity> ftpFileTransFileEntity(String filePath){
    	FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftp.listFiles(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	List<FileEntity> storeFileEntity = new ArrayList<FileEntity>();
    	for(FTPFile ff :ftpFiles){
    		FileEntity fileEntity = new FileEntity(ff.getName(), EADate.date2Str(ff.getTimestamp().getTime(),"yyyy-MM-dd HH:mm:ss"),ftpPropery.getPath(),new ArrayList<FileEntity>(),ff.isFile());
    		storeFileEntity.add(fileEntity);
    	}
    	return storeFileEntity;
    }
    
   public static void removeAllFiles(String pathName) throws Exception{
		  connectFtp(ftpPropery);
	      recursionDeleteFile(pathName);
		  closeFtp();
	}
   
    private static void recursionDeleteFile(String pathName) throws Exception
    {
    	
        try
        {
            FTPFile[] files = ftp.listFiles(pathName);
            if (null != files && files.length > 0)
            {
                for (FTPFile file : files)
                {
                    if (file.isDirectory())
                    {
                    	recursionDeleteFile(pathName + "/" + file.getName());
                        // 切换到父目录，不然删不掉文件夹
                        ftp.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));
                        ftp.removeDirectory(pathName);
                    }
                    else
                    {
                    	ftp.deleteFile(pathName + "/" + file.getName());
                    }
                }
            }
            // 切换到父目录，不然删不掉文件夹
            ftp.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));
            ftp.removeDirectory(pathName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    	
    }

	public static class FileEntity {


		private String fileName;

		private String showFileName;


		private String createTime;

		private String filePath;

		private List<FileEntity> fileEntitys;

		private boolean hasMenu = true;

		private boolean isFile = false;

		private String target;

		private String MENU_URL = "javascript:requestNextChild();";

		public  FileEntity(){}

		public FileEntity(String fileName, String createTime, String filePath, List<FileEntity> fileEntitys,boolean isFile) {
			super();
			this.fileName = fileName;
			this.createTime = createTime;
			this.filePath = filePath;
			this.fileEntitys = fileEntitys;
			this.target = fileName+";"+filePath+";"+createTime;
			this.showFileName = fileName+"&nbsp;&nbsp;（"+this.createTime+"）";
			this.isFile = isFile;
		}
		public String getMENU_URL() {
			return MENU_URL;
		}



		public boolean isFile() {
			return isFile;
		}

		public void setFile(boolean isFile) {
			this.isFile = isFile;
		}

		public void setMENU_URL(String mENU_URL) {
			MENU_URL = mENU_URL;
		}

		public String getFileName() {
			return fileName;
		}



		public String getShowFileName() {
			return showFileName;
		}

		public void setShowFileName(String showFileName) {
			this.showFileName = showFileName;
		}

		public boolean isHasMenu() {
			return hasMenu;
		}

		public void setHasMenu(boolean hasMenu) {
			this.hasMenu = hasMenu;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.target = this.fileName+";"+filePath+";"+this.createTime;
			this.filePath = filePath;
		}

		public List<FileEntity> getFileEntitys() {
			return fileEntitys;
		}

		public void setFileEntitys(List<FileEntity> fileEntitys) {
			this.fileEntitys = fileEntitys;
		}
	}
}
