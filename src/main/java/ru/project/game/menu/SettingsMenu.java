package ru.project.game.menu;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SettingsMenu implements Menuable{
    SET_FIELD_SIZE("Задать размер поля"),
    SET_INITIAL_POPULATION("Задать начальную популяцию"),
    SET_DELAY("Задать задержку вывода"),
    BACK("Назад"),
    ;

    public final static String menuDescription = "Меню настроек";
    private final String description;
    @Override
    public String getDescription() {
        return this.description;
    }

}
