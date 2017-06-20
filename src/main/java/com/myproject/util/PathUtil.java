package com.myproject.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 路径工具类
 * @author hutao
 *
 */
public class PathUtil {
	
	private static Properties properties = new Properties();
	
	static{
		InputStream in = ClassLoader.class.getResourceAsStream("/application.properties");
		try {
			properties.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件上传的目标路径
	 * @return
	 */
	public static String getFileUploadPath(){
		return properties.getProperty("file.upload.path");
	}
	
	/**
	 * 获取xml文件所在的路径
	 * @return
	 */
	public static String getXmlConfigPath(){
		return properties.getProperty("xml.config.path");
	}
	
}
