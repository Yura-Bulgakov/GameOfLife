package ru.game;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleGameBoardInitializer implements GameBoardInitializer{
    Scanner scanner = new Scanner(System.in);
    @Override
    public boolean[][] initializeGameBoard() {
        boolean[][] board = setEmptyBoard();
        return setInitialGeneration(board);
    }

    private boolean[][] setEmptyBoard(){
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        return new boolean[rows][cols];
    }

    private boolean[][] setInitialGeneration(boolean[][] board){
        System.out.println("Введите клетки с исходным поколением в формате X,Y:");
        String context = scanner.nextLine();
        Arrays.stream(context.split(" "))
                .map(cell -> cell.split(","))
                .filter(coordinates -> coordinates.length == 2)
                .forEach(coordinates -> {
                    try {
                        int x = Integer.parseInt(coordinates[0]);
                        int y = Integer.parseInt(coordinates[1]);
                        board[x][y] = true;
                    }catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Неверный формат координат: " + Arrays.toString(coordinates));
                    }
                });
        return board;
    }
}
