package com.lxx.mall.util;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author 林修贤
 * @date 2023/2/26
 * @description 处理 Excel 表格工具类
 */
public class ExcelUtil {
    public static Object getCellValue(Cell cell){
        switch (cell.getCellTypeEnum()){
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }
}
