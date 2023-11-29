package ru.project.automaton;

import ru.project.board.Board;


public class GameOfLife implements CellularAutomaton {
    @Override
    public Board makeMove(Board board) throws AutomationException {
        int rows = board.getBoard().length;
        int cols = board.getBoard()[0].length;
        boolean[][] newBoard = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countLiveNeighbors(board, i, j);
                if (board.getBoard()[i][j]) {
                    // Живая клетка
                    newBoard[i][j] = neighbors == 2 || neighbors == 3;
                } else {
                    // Мёртвая клетка
                    newBoard[i][j] = neighbors == 3;
                }
            }
        }
        return new Board(newBoard);
    }

    private static int countLiveNeighbors(Board board, int row, int col) {
        int count = 0;
        boolean[][] grid = board.getBoard();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                int r = (i + rows) % rows; // Обработка перехода через верхний и нижний края
                int c = (j + cols) % cols; // Обработка перехода через левый и правый края

                if (!(i == row && j == col) && board.getBoard()[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }


}
