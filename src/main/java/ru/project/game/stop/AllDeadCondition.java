package ru.project.game.stop;

import ru.project.board.Board;

public class AllDeadCondition implements GameOverCondition {
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
