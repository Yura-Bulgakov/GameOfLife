package ru.game;

public class CellSurvivalEndConditionChecker implements GameOverConditionChecker {
    @Override
    public boolean isGameOver(boolean[][] board) {
        return !hasLiveCells(board);
    }

    private boolean hasLiveCells(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean cell : row) {
                if (cell) {
                    return true;
                }
            }
        }
        return false;
    }
}
