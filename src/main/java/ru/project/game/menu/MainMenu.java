package ru.project.game.menu;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MainMenu implements Menuable{
    START_GAME("Запустить игру"),
    SHOW_SETTINGS("Посмотреть настройки"),
    SET_SETTINGS("Задать настройки"),
    EXIT("Выйти"),
    ;

    private final String description;
    public final static String menuDescription = "Главное меню";
    @Override
    public String getDescription() {
        return this.description;
    }

}
