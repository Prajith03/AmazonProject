package MobileSearchAutomation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

    public static List<String[]> readData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\datas2.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        List<String[]> data = new ArrayList<>();

        for (Row row : sheet) {
            String[] rowData = new String[row.getLastCellNum()];
            for (int i = 0; i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    rowData[i] = cell.toString();
                } else {
                    rowData[i] = "";
                }
            }
            data.add(rowData);
        }

        workbook.close();
        file.close();
        return data;
    }

    public static void writeData(String filePath, String sheetName, String[][] data) throws IOException {
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\datas2.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            sheet = workbook.createSheet(sheetName);
        }

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.getRow(i);
            if(row == null){
                row = sheet.createRow(i);
            }
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.getCell(j);
                if(cell == null){
                    cell = row.createCell(j);
                }
                cell.setCellValue(data[i][j]);
            }
        }

        FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "\\TestData\\datas2.xlsx");
        workbook.write(outputStream);
        workbook.close();
        inputStream.close();
        outputStream.close();
    }
}