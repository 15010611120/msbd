package com.yxd.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelHelper {
	/**
	 * 生成Excel(2007版本以上)
	 * 
	 * @param JsonData
	 * @param title
	 * @param filePath
	 * @return
	 */
	public static String createExcel(List<LinkedHashMap<String, String>> valuesList, String title, String fileName,HttpServletResponse response) {
		String Result = "";
		String filePath = "C:\\Users\\Xiaodong\\Downloads\\" + fileName + ".xlsx";
		try {
			// System.out.println("############ 开始创建EXCEL对象：" + );
			XSSFWorkbook workBook = new XSSFWorkbook();// 创建工作薄
			XSSFSheet sheet = workBook.createSheet();
			workBook.setSheetName(0, title);// 工作簿名称
			XSSFFont font = workBook.createFont();
			font.setColor(XSSFFont.COLOR_NORMAL);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("yahei");
			XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			XSSFRow titleRow = sheet.createRow((short) 0);// 第一行标题
			LinkedHashMap<String, String> map = valuesList.get(0);
			Integer i = 0;
			// System.out.println("############ 开始拼装EXCEL数据：" );
			for (Entry<String, String> entry : map.entrySet()) {
				XSSFCell cell = titleRow.createCell(i, 0);
				cell.setCellStyle(cellStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(entry.getKey());
				sheet.autoSizeColumn(i, true);
				//sheet.setColumnWidth(m, entry.getKey().getBytes().length*2*256);
				i++;
			}
			// System.out.println("############ 开始填充EXCEL数据：" );
			XSSFCellStyle style = workBook.createCellStyle();// 创建格式
			XSSFFont fontBody = workBook.createFont();
			fontBody.setFontName("yahei");
			style.setFont(fontBody);
			for (int j = 0; j < valuesList.size(); j++) {
				Map<String, String> vmap = valuesList.get(j);
				XSSFRow row = sheet.createRow((short) j + 1);
				int k = 0;
				for (Entry<String, String> entry : vmap.entrySet()) {
					XSSFCell cell = row.createCell(k, 0);
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(entry.getValue());
					// style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					cell.setCellStyle(style);
					sheet.autoSizeColumn(k, true);
					// sheet.autoSizeColumn(k); // 自动调整宽度
					k++;
				}
			}
			// System.out.println("############ EXCEL数据填充完成：" + );

			File file = new File(filePath);
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			outStream.flush();
			outStream.close();

			// System.out.println("############ 写文件流完成：" + );
			// 取得文件名。
			fileName = fileName + ".xlsx";
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1" ));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

			// System.out.println("############ 输出文件流完成：" );

			File fileD = new File(filePath);
			if (fileD.isFile() && fileD.exists()) fileD.delete();

			// System.out.println("############ 删除文件完成：");
			Result = "0";
		} catch (Exception e) {
			File fileD = new File(filePath);
			if (fileD.isFile() && fileD.exists()) fileD.delete();
			e.printStackTrace();
			Result = "-1";
		}
		return Result;
	}
	
	

}
