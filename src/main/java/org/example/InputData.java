package org.example;

import java.util.List;

/**
 * Record class to encapsulate data extracted from an Excel sheet.
 * It contains three lists representing data from three columns.
 *
 * @param columnA List of Integer values from column A.
 * @param columnB List of Integer values from column B.
 * @param columnC List of Integer values from column C.
 */
public record InputData(List<Integer> columnA, List<Integer> columnB, List<Integer> columnC) {

    /**
     * Overridden toString method to provide a string representation of the ExcelData object.
     * @return A string representation of the ExcelData object.
     */
    @Override
    public String toString() {
        return "ExcelData{" +
                "columnA=" + columnA +
                ", columnB=" + columnB +
                ", columnC=" + columnC +
                '}';
    }
}