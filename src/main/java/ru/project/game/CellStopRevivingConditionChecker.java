package ru.project.game;

import lombok.AllArgsConstructor;
import ru.project.board.Board;

@AllArgsConstructor
public class CellStopRevivingConditionChecker implements GameOverConditionChecker{

    private Board oldBoard;

    @Override
    public boolean isGameOver(Board board) {
        return isStopReviving(board);
    }

    private boolean isStopReviving(Board board){
        return board.equals(oldBoard);
    }

}
