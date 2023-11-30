package ru.project.game.stop;

import ru.project.board.Board;
import ru.project.board.BoardBuffer;

public class RepeatPatternCondition  implements GameOverCondition {
    private final BoardBuffer boardBuffer;

    public RepeatPatternCondition(BoardBuffer boardBuffer) {
        this.boardBuffer = boardBuffer;
    }

    @Override
    public boolean isGameOver(Board board) {
        boolean isOver = isRepeating(board);
        boardBuffer.push(board);
        return isOver;
    }

    private boolean isRepeating(Board board) {
       return boardBuffer.has(board);
    }
}
