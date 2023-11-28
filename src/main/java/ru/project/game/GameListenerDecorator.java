package ru.project.game;

import ru.project.board.Board;
import ru.project.ui.ConsoleUserListener;
import ru.project.ui.menu.Menuable;
import ru.project.ui.UserListener;

import java.util.Arrays;

public class GameListenerDecorator implements UserListenerDecorator {
    private final UserListener userListener;

    public GameListenerDecorator() {
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
        String[] menuDescriptions = Arrays.stream(menuElements)
                .map(Menuable::getDescription)
                .toArray(String[]::new);
        String choice = pickMenuItem(prompt, menuDescriptions);
        T selectedItem = Arrays.stream(menuElements)
                .filter(item -> item.getDescription().equals(choice))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Неизвестная ошибка, не удалось определить пункт меню: " + choice));
        return selectedItem;
    }
}
