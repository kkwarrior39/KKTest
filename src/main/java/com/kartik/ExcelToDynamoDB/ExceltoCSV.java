package com.kartik.ExcelToDynamoDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExceltoCSV {
	@SuppressWarnings("resource")
	public static void echoAsCSV(Sheet sheet) throws IOException {
		Row row = null;
		File output = new File("/home/moglix/Desktop/CSVtext.csv");
        FileOutputStream fos = new FileOutputStream(output);
        
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                bw.write("\"" + row.getCell(j) + "\";");
            }
            bw.newLine();
        }
        System.out.println("Success");
        bw.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void excelCSV() {
        InputStream inp = null;
        
        try {
            inp = new FileInputStream("/home/moglix/Desktop/CSVtest.xls");
            Workbook wb = WorkbookFactory.create(inp);

            for(int i=0;i<wb.getNumberOfSheets();i++) {
                //System.out.println(wb.getSheetAt(i).getSheetName());
                echoAsCSV(wb.getSheetAt(i));
            }
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExceltoCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExceltoCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExceltoCSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                Logger.getLogger(ExceltoCSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}