package ru.project.game.stop;

import ru.project.board.Board;

public interface GameOverConditionChecker {
    boolean isGameOver(Board board);
}
