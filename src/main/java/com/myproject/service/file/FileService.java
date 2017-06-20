package com.myproject.service.file;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.constant.BaseConstant;
import com.myproject.dao.FileMapper;
import com.myproject.emun.ResultType;
import com.myproject.util.BaseUtil;
import com.myproject.util.PathUtil;
import com.myproject.util.Resultinfo;

@Service
public class FileService {
	
	@Resource
	private FileMapper fileMapper;
	
	public Resultinfo upload(MultipartFile file,String memo){
		if(file.getOriginalFilename()=="" || file.getSize()==0){
			return new Resultinfo("file_list", "上传文件不能为空");
		}
		//判断文件大小
		if(file.getSize()>=BaseConstant.FILE_SIZE){
			return new Resultinfo("file_list", "文件超出限制大小");
		}
		//获取文件名称
		String fileName = file.getOriginalFilename();
		//获取文件名后缀
		String suffix = BaseUtil.getSuffix(fileName);
		//判断该文件是否可以上传
		if(!BaseUtil.array2contain(BaseConstant.FILE_EXT, suffix)){
			return new Resultinfo("file_list", "不允许上传该文件类型文件");
		}
		//获取保存目录
		String savePath = PathUtil.getFileUploadPath();
		File f = new File(savePath);
		//判断目录是否存在,并且该目录是否是文件夹
		if(!f.exists() && !f.isDirectory()){
			f.mkdir();
		}
		//开始上传文件
		try {
			savePath = savePath+"/"+file.getOriginalFilename();
			file.transferTo(new File(savePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果上传成功，则更新到数据库，提供下载服务
		int result = save(savePath,fileName,memo,new Date(),"lustboy");
		if(result==1){
			return new Resultinfo("file_list", "上传成功");
		}
		return new Resultinfo("file_list", "上传成功，但数据保存发生错误");
	}
	
	/**
	 * 保存上传文件的信息
	 * @return
	 */
	public int save(String path,String fileName,String memo,Date createDate,String createUser){
		int i = fileMapper.save(path, fileName, memo, createDate, createUser);
		return i==1?ResultType.Success.getValue():ResultType.Fail.getValue();
	}
	
	/**
	 * 获取下载列表
	 * @return
	 */
	public Set<Map<String, Object>> getDownLoadList(){
		Set<Map<String, Object>> set = fileMapper.getDownLoadList();
		return set;
	}
}
