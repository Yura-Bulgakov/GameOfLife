package ru.project.game.stop;

import ru.project.board.Board;

public interface GameOverCondition {
    boolean isGameOver(Board board);
}
