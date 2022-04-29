package com.ydata.herokuApp.web.common.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <pre>
 * 엑셀 저장용 UTIL
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.15.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.15.
 * @version 1.0
 */

public class ExcelUtil {

    @SuppressWarnings("resource")
    public int getExcelRows(File file) {

        int rows = 0;

        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }
}
