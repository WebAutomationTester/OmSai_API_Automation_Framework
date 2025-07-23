package DataByExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {

    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList<String> ar =  new ArrayList();
        String path = "E:\\Selenium\\API Test New\\Postman\\Sai_API_Automation\\OmSai_BDD_API_Framework\\src\\test\\java\\TestData\\DataSheet.xlsx";
        XSSFWorkbook workbook;
        try (FileInputStream fis = new FileInputStream(path)) {
            workbook = new XSSFWorkbook(fis);
        }

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("LoginData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // Identify testcases column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell value = cells.next();
                    if(value.getStringCellValue().equalsIgnoreCase("Data2")) {
                      column = k;
                    }
                    k++;
                }
                System.out.println(column);

                // Once column is identified then scan entire testcase column to identify Purchase testcase row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                       System.out.println("At purchase");
                       // After you grab purchase testcase row = pull all the data of the row and feed into test
                        Iterator<Cell> cellValue =  r.cellIterator();
                        while(cellValue.hasNext()) {
                            Cell Data = cellValue.next();
                            if(Data.getCellType() == CellType.STRING) {
                                ar.add(Data.getStringCellValue());
                            } else {
                                ar.add(NumberToTextConverter.toText(Data.getNumericCellValue()));
                            }
                        }
                    }
                }
                System.out.println("Data Arraylist is: " + ar);
            }
        }
        return ar;
    }
}
