package ru.project.game;

import ru.project.board.Board;

public interface GameOverConditionChecker {
    boolean isGameOver(Board board);
}
