package gov.cnao.ao.ai.bfs.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author fangbin
 */
public class excelUtil {
	
	/**
	 * 大标题样式
	 * @param wb
	 * @param cellStyle
	 * @return
	 */
	public static CellStyle desStyle(HSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeight((short) 800);
        font.setFontHeightInPoints((short) 16);// 设置字体大小
        cellStyle.setFont(font);
        return cellStyle;
    }

	/**
	 * 标题样式
	 * @param wb
	 * @param cellStyle
	 * @return
	 */
	public static CellStyle titleStyle(HSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        return cellStyle;
    }
    
    /**
     * 列样式
     * @param wb
     * @param ccellStyle
     * @return
     */
	public static CellStyle celStyle(HSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        Font font = wb.createFont();
        font.setFontHeight((short) 800);
        font.setFontHeightInPoints((short) 12);// 设置字体大小
        cellStyle.setFont(font);
        return cellStyle;
    }
}
