package com.myproject.controller.file;

import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.annotation.FormData;
import com.myproject.service.file.FileService;
import com.myproject.util.Resultinfo;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Resource
	private FileService fileService;
	
	@RequestMapping("/list")
	public String list(){
		return "file_list";
	}
	
	@RequestMapping("/upload")
	@FormData
	public Resultinfo upload(Model model,@RequestParam(value="file",required=false)MultipartFile file,
						 @RequestParam(value="memo",required=false)String memo){
		return fileService.upload(file, memo);
	}
	
	@RequestMapping("/download")
	public Resultinfo downList(Model model){
		Set<Map<String, Object>> set = fileService.getDownLoadList();
		model.addAttribute("set", set);
		model.addAttribute("flag", true);
		return new Resultinfo("file_list");
	}
	
	@RequestMapping(value="/down")
	public ResponseEntity<byte[]> download(@RequestParam(value="path")String filepath) throws Exception{
		File file = new File(filepath);
		if(file.exists() && file.isFile()){
			filepath = new String(filepath.getBytes("gb2312"), "iso-8859-1");
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", filepath);
			return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers,HttpStatus.CREATED);
		}else{
			return null;
		}
	}
}
