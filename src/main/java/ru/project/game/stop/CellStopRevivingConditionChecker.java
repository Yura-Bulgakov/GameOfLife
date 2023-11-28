package ru.project.game.stop;

import lombok.AllArgsConstructor;
import ru.project.board.Board;

@AllArgsConstructor
public class CellStopRevivingConditionChecker implements GameOverConditionChecker{

    private Board oldBoard;

    @Override
    public boolean isGameOver(Board board) {
        boolean isOver = isStopReviving(board);
        this.oldBoard = board;
        return isOver;
    }

    private boolean isStopReviving(Board board){
        return board.equals(oldBoard);
    }

}
