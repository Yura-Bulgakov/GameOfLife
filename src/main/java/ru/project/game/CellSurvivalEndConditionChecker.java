package ru.project.game;

import ru.project.board.Board;
import ru.project.game.GameOverConditionChecker;

public class CellSurvivalEndConditionChecker implements GameOverConditionChecker {
    @Override
    public boolean isGameOver(Board board) {
        return !hasLiveCells(board.getBoard());
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
