package ru.project.game;

import ru.project.ui.menu.Menuable;
import ru.project.ui.UserListener;

public interface UserListenerDecorator extends UserListener {
    <T extends Enum<T> & Menuable> T pickMenuItem(String prompt, Class<T> menuItems);
}
