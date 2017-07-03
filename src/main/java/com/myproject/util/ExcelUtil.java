package com.myproject.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * excel导入导出
 * @author ht
 *
 */
public class ExcelUtil {
	
	private static WritableWorkbook writableWorkbook = null; 
	
	public static void excelOutput(Class<?> className,List<?> list){
		if(className==null){
			System.out.println("名称不能为空！");
			return;
		}
		
		String sheetName = className.getSimpleName();
		System.out.println(sheetName);
		//设置导出文件的名称
		String fileName = sheetName+".xls";
		try {
			writableWorkbook = Workbook.createWorkbook(new File("/"+fileName));
			Field[] fields =  className.getFields();
			WritableSheet sheet = writableWorkbook.createSheet(sheetName, 0);
			//设置列名称
			sheet = setSheetLabelName(fields,sheet);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置列的名称
	 * @param fields
	 * @param sheet
	 */
	private static WritableSheet setSheetLabelName(Field[] fields,WritableSheet sheet) {
		for (int i = 0; i < fields.length; i++) {
			Label label = new Label(i, 0, fields[i].getName());
			try {
				sheet.addCell(label);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		return sheet;
	}
}
