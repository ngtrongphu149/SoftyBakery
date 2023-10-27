package com.poly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class ExcelReaderUtil {
    public static void read(String filePath) throws EncryptedDocumentException, InvalidFormatException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook wb = WorkbookFactory.create(fis)) {
            Sheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellValue cellValue = formulaEvaluator.evaluate(cell);
                    System.out.print((int) cellValue.getNumberValue());
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
