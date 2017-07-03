package com.myproject.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 文件读写
 * @author hutao
 *
 */
public class IO {
	
	public static void main(String[] args) throws IOException {
		readFile();
	}
	
	public static void readFile() throws IOException{
		File file = new File("C:/Users/gaojk/Desktop/基本信息.txt");
		if(file.isFile() && file.exists()){
			InputStream in = new FileInputStream(file);
			OutputStream out = new FileOutputStream("C:/Users/gaojk/Desktop/file.txt");
			int len = 0;
			byte[] b = new byte[1024];
			while ((len=in.read(b))!=-1) {
				out.write(b, 0, len);
				System.out.write(b, 0, len);
			}
			out.close();
			in.close();
			
		}
	}
}
