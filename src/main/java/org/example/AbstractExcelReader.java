package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Abstract class providing a framework for reading Excel files.
 * @param <T> Type of data that will be extracted from the Excel cells.
 */
public abstract class AbstractExcelReader<T> {
    protected Workbook workbook;  // Workbook object to represent the Excel file.

    /**
     * Constructor that initializes the Workbook by reading an Excel file from the given file path.
     * @param filePath Path to the Excel file.
     * @throws IOException If an I/O error occurs.
     */
    public AbstractExcelReader(String filePath) throws IOException {
        // Use a FileInputStream to read the Excel file and initialize the Workbook.
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            this.workbook = new XSSFWorkbook(fileInputStream);
        }
    }

    /**
     * Retrieves a specific sheet from the workbook by its index.
     * @param sheetIndex Index of the sheet to retrieve.
     * @return The Sheet object at the specified index.
     */
    protected Sheet getSheet(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     * Abstract method to be implemented by subclasses to define how to extract a value from a cell.
     * @param cell Cell from which to extract the value.
     * @return The extracted value of type T.
     */
    protected abstract T getValueFromCell(Cell cell);

    /**
     * Abstract method to be implemented by subclasses to process a sheet and extract the required data.
     * @param sheetIndex Index of the sheet to process.
     * @return An instance of ExcelData containing the processed data.
     * @throws Exception If an error occurs during processing.
     */
    public abstract ExcelData processSheet(int sheetIndex) throws Exception;
}