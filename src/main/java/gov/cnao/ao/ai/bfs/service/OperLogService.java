package gov.cnao.ao.ai.bfs.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.entity.Person;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.util.excelUtil;

/**
 * @author fangbin
 */

@Service
public class OperLogService {

	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private HttpServletResponse response;
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	public List<OperLog> queryOperLog(OperLog operLog) {
		return operLogMapper.queryOperLog(operLog);
	}

	/**
	 * 操作日志新增
	 * @param operLog
	 * @return
	 */
	public OperLog insertOperLog(OperLog operLog) {
		operLogMapper.insertOperLog(operLog);
		return operLog;
	}

	/**
	 * 操作日志导出
	 * @param operLog
	 */
	public void exportOperLog(OperLog operLog) {
		HSSFWorkbook workBook = new HSSFWorkbook();
		ServletOutputStream out = null;
		String[] titles = {"序号", "用户名称", "日志类型", "登录IP", "机构名称", "功能标识", "日志内容"};
		List<OperLog> list = operLogMapper.queryOperLog(operLog);
		
        HSSFSheet sheet = workBook.createSheet("人员信息");
        Row desRow = sheet.createRow(0);
        sheet.addMergedRegion(new Region(0, (short) 0, 0, titles.length > 0 ? (short) (titles.length - 1) : (short) 0));
        Cell descell = desRow.createCell(0);
        descell.setCellValue("操作日志信息表");
        descell.setCellStyle(excelUtil.desStyle(workBook));
        try {
            // 标题信息
            Row titleRow = sheet.createRow(1);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(excelUtil.titleStyle(workBook));
                titleRow.setHeight((short) 600);
                sheet.setColumnWidth(i, 4400);
            }
            Cell cell = null;
            for (int j = 0; j < list.size(); j++) {
                OperLog log = list.get(j);
                Row row = sheet.createRow(j + 2);
                row.setHeight((short) 800);
                String[] cellContents = {
                        String.valueOf(j + 1),
                        log.getUserNm(),
                        log.getLogType(),
                        log.getLoginIp(),
                        log.getOrgNm(),
                        log.getFunFlg(),
                        log.getLogCont()
                };

                for (int i = 0; i < cellContents.length; i++) {
                    String content = cellContents[i];
                    cell = row.createCell(i);
                    cell.setCellValue(content);
                    cell.setCellStyle(excelUtil.celStyle(workBook));
                }
            }
            // 文件名
            StringBuffer fileName = new StringBuffer("操作日志信息表-");
            fileName.append(DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
            fileName.append(".xls");
            response.setHeader("Content-disposition", "attachment;filename=" + CommonUtil.toUTF8String(fileName.toString()));
            response.flushBuffer();
            out = response.getOutputStream();
            workBook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}