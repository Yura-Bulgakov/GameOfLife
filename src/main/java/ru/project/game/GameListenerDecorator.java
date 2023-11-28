package ru.project.game;

import ru.project.board.Board;
import ru.project.ui.ConsoleUserListener;
import ru.project.ui.Menuable;
import ru.project.ui.UserListener;

import java.util.Arrays;

public class GameListenerDecorator implements UserListenerDecorator {
    private final UserListener userListener;

    public GameListenerDecorator(UserListener userListener) {
        this.userListener = new ConsoleUserListener();
    }

    @Override
    public int getInt(String prompt) {
      return userListener.getInt(prompt);
    }

    @Override
    public void showMessage(String message) {
        userListener.showMessage(message);
    }

    @Override
    public void showBoard(Board board) {
        userListener.showBoard(board);
    }

    @Override
    public String pickMenuItem(String prompt, String... menuItems) {
        return userListener.pickMenuItem(prompt, menuItems);
    }

    @Override
    public <T extends Enum<T> & Menuable> T pickMenuItem(String prompt, Class<T> menuItems) {
        T[] menuElements = menuItems.getEnumConstants();
        showMessage(prompt);
        for (int i = 0; i < menuElements.length; i++) {
            showMessage((i + 1) + ". " + menuElements[i].getDescription());
        }
        int choice;
        do {
            choice = getInt("Выберите пункт меню:");
        } while (choice < 1 || choice > menuElements.length);

        return menuElements[choice - 1];
    }
}
