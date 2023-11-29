package ru.project.automaton;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

public interface CellularAutomaton {
    Board makeMove (Board board) throws AutomationException;
}
