package ru.project.ui;

import ru.project.board.Board;

public interface UserListener {
    int getInt(String prompt);
    void showMessage(String message);
    void showBoard(Board board);

    String pickMenuItem(String prompt, String... menuItems);

}
