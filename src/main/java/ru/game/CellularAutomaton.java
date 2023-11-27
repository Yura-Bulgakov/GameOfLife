package ru.game;

public interface CellularAutomaton {
    boolean[][] makeMove (boolean[][] board) throws AutomationException;
}
