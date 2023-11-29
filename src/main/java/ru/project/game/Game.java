package ru.project.game;

import ru.project.automaton.CellularAutomaton;
import ru.project.board.Coordinate;
import ru.project.board.factory.BoardFactory;
import ru.project.board.factory.BoardFactoryFactory;
import ru.project.game.settings.Settings;
import ru.project.game.settings.SettingsYamlHelper;
import ru.project.game.stop.GameOverConditionChecker;
import ru.project.ui.menu.MainMenu;
import ru.project.ui.menu.PopulationMenu;
import ru.project.ui.menu.SettingsMenu;

import java.io.IOException;

public class Game {
    private CellularAutomaton automaton;
    private GameOverConditionChecker[] gameOverCheckers;
    private UserListenerDecorator decorator;
    private BoardFactoryFactory boardFactoryFactory;
    private Settings settings;
    private String filename;
    private SettingsYamlHelper settingsYamlHelper;



    public Game(String filename
            ,CellularAutomaton cellularAutomaton
            , GameOverConditionChecker[] gameOverCheckers
            ,BoardFactoryFactory boardFactoryFactory) throws IOException {
        this.decorator = new GameListenerDecorator();
        this.filename = filename;
        this.settingsYamlHelper = new SettingsYamlHelper();
        this.settings = settingsYamlHelper.load(this.filename);
        this.automaton = cellularAutomaton;
        this.gameOverCheckers = gameOverCheckers;
        this.boardFactoryFactory = boardFactoryFactory;
    }

    private void chooseMainMenuItem() throws IOException {
        MainMenu menu = decorator.pickMenuItem("", MainMenu.class);
        switch (menu){
            case START_GAME:
            {
                startGame();
                break;
            }
            case SET_SETTINGS:
            {
                chooseSettingsMenuItem();
                break;
            }
            case GET_SETTINGS:
            {
                getSettings();
                chooseMainMenuItem();
                break;
            }
            case EXIT:
            {
                System.exit(0);
            }

        }
    }

    private void getSettings(){
        boolean hasCellAmount = settings.getFirstGeneration().getCellAmount() > 0;
        boolean random = settings.getFirstGeneration().isRandom();
        StringBuilder message = new StringBuilder(String.format("Размеры поля %d x %d \n",
                settings.getRows(), settings.getCols()));
        if (random){
            message.append(String.format("Случайные клетки начальной популяции %b \n", true));
            if (!hasCellAmount){
                message.append(String.format("Случайное количество клеток в начальной популяции %b \n", true));
            }else {
                message.append(String.format("Количество клеток в начальной популяции %d \n",
                        settings.getFirstGeneration().getCellAmount()));
            }
        } else {
            message.append(String.format("Случайные клетки начальной популяции %b \n" +
                    "Позиции клеток: ", false));
            for (Coordinate coordinate: settings.getFirstGeneration().getCoordinates()) {
                message.append(coordinate.toString());
            }
        }
        decorator.showMessage(message.toString());
    }


    private void chooseSettingsMenuItem() throws IOException {
        SettingsMenu menu = decorator.pickMenuItem("", SettingsMenu.class);
        switch (menu){
            case SET_FIELD_SIZE:
            {
                setFieldSize();
                chooseSettingsMenuItem();
            }
            case SET_INITIAL_POPULATION:
            {
                choosePopulationMenuItem();
                break;
            }
            case BACK:{
                chooseMainMenuItem();
                break;
            }
        }
    }

    private void setFieldSize() throws IOException {
        settings.setRows(decorator.getInt("Введите количество строк: "));
        settings.setCols(decorator.getInt("Введите количество столбцов: "));
        settingsYamlHelper.save(settings, filename);
    }


    private void choosePopulationMenuItem() throws IOException {
        PopulationMenu menu = decorator.pickMenuItem("", PopulationMenu.class);
        switch (menu){
            case RANDOM:
            {
                setInitialGenerationRandomSetting();
                choosePopulationMenuItem();
            }
            case RANDOM_SET_AMOUNT:
            {
                setInitialGenerationRandomCellAmount();
                choosePopulationMenuItem();
            }
            case ENTER_COORDINATES:
            {
                setInitialGenerationCellCoordinates();
                choosePopulationMenuItem();
            }
            case BACK:{
                chooseSettingsMenuItem();
                break;
            }
        }
    }

    private void setInitialGenerationRandomSetting(){
        if (settings.getFirstGeneration().isRandom()){
            settings.getFirstGeneration().setRandom(false);
            decorator.showMessage("Распределение живых клеток в первом поколении установлено на случайное");
        }else {
            settings.getFirstGeneration().setRandom(true);
            decorator.showMessage("Распределение живых клеток в первом поколении вручную");
        }
    }

    private void setInitialGenerationRandomCellAmount(){
       settings.getFirstGeneration().setCellAmount(decorator.getInt("Введите число клеток"));
    }

    private void setInitialGenerationCellCoordinates(){
        int count = decorator.getInt("Введите количество клеток:");
        Coordinate[] coordinates = new Coordinate[count];
        for (int i = 0; i < count; i++) {
            coordinates[i].setRow(decorator.getInt(String.format("Введите номер строки для клетки %d",i)));
            coordinates[i].setCol(decorator.getInt(String.format("Введите номер столбца для клетки %d",i)));
        }
        settings.getFirstGeneration().setCoordinates(coordinates);
    }


    private void startGame(){

    }



}
