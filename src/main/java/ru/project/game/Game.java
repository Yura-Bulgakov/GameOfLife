package ru.project.game;

import ru.project.automaton.CellularAutomaton;
import ru.project.board.factory.BoardFactory;
import ru.project.game.settings.Settings;
import ru.project.game.settings.SettingsYamlHelper;
import ru.project.game.stop.GameOverConditionChecker;

public class Game {
    private CellularAutomaton automaton;
    private GameOverConditionChecker[] gameOverCheckers;
    private UserListenerDecorator decorator;
    private BoardFactory boardFactory;
    private Settings settings;
    private SettingsYamlHelper settingsYamlHelper;
    private String filename;


    public Game(String filename, GameOverConditionChecker[] gameOverCheckers){
        decorator = new GameListenerDecorator();


    }

}
