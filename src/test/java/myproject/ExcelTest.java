package myproject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myproject.model.JdbcEntity;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelTest {
	
	public static void excelOut(){
		WritableWorkbook bWorkbook = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			bWorkbook = Workbook.createWorkbook(new File("D:/myExcel.xls"));
			WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);
			List<JdbcEntity> list = new ArrayList<JdbcEntity>();
			list.add(new JdbcEntity(1, "hutao", true, new Date()));
			list.add(new JdbcEntity(2, "lustboy", true, new Date()));
			list.add(new JdbcEntity(3, "timer", false, new Date()));
			list.add(new JdbcEntity(4, "best", false, new Date()));
			//列、行、列名
			Label labelId = new Label(0, 0, "编号(id)");
			Label labelName = new Label(1, 0, "姓名(name)");
			Label labelStatus = new Label(2, 0, "状态(status)");
			Label labelCreateTime = new Label(3, 0, "创建时间(createTime)");
			sheet.addCell(labelId);
			sheet.addCell(labelName);
			sheet.addCell(labelStatus);
			sheet.addCell(labelCreateTime);
			for (int i = 0; i < list.size(); i++) {
				Label data_id = new Label(0, i+1,String.valueOf(list.get(i).getId()));
				Label data_name = new Label(1, i+1,String.valueOf(list.get(i).getName()));
				Label data_status = new Label(2, i+1,String.valueOf(list.get(i).getStatus()));
				Label data_time = new Label(3, i+1,sdf.format(list.get(i).getCreateTime()));
				sheet.addCell(data_id);
				sheet.addCell(data_name);
				sheet.addCell(data_status);
				sheet.addCell(data_time);
			}
			bWorkbook.write();
			bWorkbook.close();
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		excelOut();
	}
	
}
