package ru.project.ui;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SettingsMenu implements Menuable{
    SET_FIELD_SIZE("Задать размер поля"),
    SET_INITIAL_POPULATION("Задать начальную популяцию"),
    BACK("Назад");

    private final String description;
    @Override
    public String getDescription() {
        return this.description;
    }
}
