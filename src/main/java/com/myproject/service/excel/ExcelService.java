package com.myproject.service.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;



import com.myproject.dao.JdbcMapper;
import com.myproject.model.JdbcEntity;

@Service
public class ExcelService {
	
	@Resource
	JdbcMapper jdbcMapper;
	
	public boolean excelExport(){
		WritableWorkbook bWorkbook = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fileName = "D:\\myExcel.xls";
		try {
			bWorkbook = Workbook.createWorkbook(new File(fileName));
			WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);
			List<JdbcEntity> list = jdbcMapper.getAll();
			Label labelId = new Label(0, 0, "编号(id)");
			Label labelName = new Label(1, 0, "姓名(name)");
			Label labelStatus = new Label(2, 0, "状态(status)");
			Label labelCreateTime = new Label(3, 0, "创建时间(createTime)");
			sheet.addCell(labelId);
			sheet.addCell(labelName);
			sheet.addCell(labelStatus);
			sheet.addCell(labelCreateTime);
			for(int i=0;i<list.size();i++){
				sheet.addCell(new Label(0,i+1,String.valueOf(list.get(i).getId())));
				sheet.addCell(new Label(1,i+1,String.valueOf(list.get(i).getName())));
				sheet.addCell(new Label(2,i+1,String.valueOf(list.get(i).getStatus())));
				sheet.addCell(new Label(3,i+1,sdf.format(list.get(i).getCreateTime())));
			}
			bWorkbook.write();
			bWorkbook.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
