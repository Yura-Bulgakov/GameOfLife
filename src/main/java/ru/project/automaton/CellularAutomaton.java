package ru.project.automaton;

import ru.project.board.Board;

public interface CellularAutomaton {
    Board makeMove (boolean[][] board) throws AutomationException;
}
