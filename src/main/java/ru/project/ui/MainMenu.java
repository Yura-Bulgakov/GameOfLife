package ru.project.ui;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MainMenu implements Menuable{
    START_GAME("Запустить игру"),
    SETTINGS("Задать настройки"),
    EXIT("Выйти");

    private final String description;
    @Override
    public String getDescription() {
        return this.description;
    }
}
