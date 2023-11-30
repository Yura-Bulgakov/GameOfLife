package ru.project.game.menu;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PopulationMenu implements Menuable{
    RANDOM("Живые клетки располагаются случайным образом"),
    RANDOM_SET_AMOUNT("Установить количество клеток, клетки располагаются случайным образом"),
    ENTER_COORDINATES("Задать начальную популяцию"),
    BACK("Назад"),
    ;

    public final static String menuDescription = "Меню первой популяции";
    private final String description;
    @Override
    public String getDescription() {
        return this.description;
    }

}
