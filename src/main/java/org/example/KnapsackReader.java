package org.example;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading and processing data from a specific Excel file format.
 * Extends AbstractExcelReader to provide type-specific processing for Integer values.
 */
public class KnapsackReader extends AbstractExcelReader<Integer> {

    /**
     * Constructor that initializes the Workbook by reading an Excel file from the given file path.
     * @param filePath Path to the Excel file.
     * @throws IOException If an I/O error occurs.
     */
    public KnapsackReader(String filePath) throws IOException {
        super(filePath);
    }

    /**
     * Extracts an Integer value from a given cell.
     * @param cell The cell from which to extract the value.
     * @return The extracted Integer value, or null if the cell is not numeric.
     */
    @Override
    protected Integer getValueFromCell(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return null;
    }

    /**
     * Processes a specific sheet in the Excel file and extracts data into lists.
     * @param sheetIndex Index of the sheet to process.
     * @return An instance of ExcelData containing the processed data from the sheet.
     * @throws Exception If there is an error during processing, such as mismatched column sizes.
     */
    @Override
    public InputData processSheet(int sheetIndex) throws Exception {
        System.out.println("Verarbeite Blatt mit Index: " + sheetIndex);
        Sheet sheet = getSheet(sheetIndex);
        List<Integer> columnA = new ArrayList<>();
        List<Integer> columnB = new ArrayList<>();
        List<Integer> columnC = new ArrayList<>();

        // Iterate over the rows, starting from 1 to skip the header row
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Integer valueA = getValueFromCell(row.getCell(0));
                Integer valueB = getValueFromCell(row.getCell(1));
                Integer valueC = getValueFromCell(row.getCell(2));

                if (valueA != null) {
                    columnA.add(valueA);
                }
                if (valueB != null) {
                    columnB.add(valueB);
                }
                if (valueC != null) {
                    columnC.add(valueC);
                }
            }
        }

        // Check if the sizes of columns B and C are equal
        if (columnB.size() != columnC.size()) {
            throw new Exception("Die Anzahl der Integer in Spalte B und C ist nicht gleich groß!");
        }

        // Return the processed data encapsulated in an ExcelData object
        return new InputData(columnA, columnB, columnC);
    }
}
