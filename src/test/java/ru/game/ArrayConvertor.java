package ru.game;

public class ArrayConvertor {
    public static boolean[][] convertStringToArray(String input) {
        String[] rows = input.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();
        boolean[][] resultArray = new boolean[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                resultArray[i][j] = rows[i].charAt(j) == '1';
            }
        }
        return resultArray;
    }
}
