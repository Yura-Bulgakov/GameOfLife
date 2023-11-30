package ru.project;

import ru.project.automaton.CellularAutomaton;
import ru.project.automaton.GameOfLife;
import ru.project.board.BoardBuffer;
import ru.project.board.factory.BoardFactoryException;
import ru.project.game.Application;
import ru.project.game.settings.SettingsStorage;
import ru.project.game.settings.YamlSettingsStorage;
import ru.project.game.stop.AllDeadCondition;
import ru.project.game.stop.GameOverCondition;
import ru.project.game.stop.RepeatPatternCondition;
import ru.project.game.stop.StuckCondition;
import ru.project.ui.ConsoleUserListener;
import ru.project.ui.UserListener;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, BoardFactoryException, InterruptedException {
        UserListener userListener = new ConsoleUserListener();
        SettingsStorage settingsStorage = new YamlSettingsStorage("settings.yaml");
        CellularAutomaton automaton = new GameOfLife();
        BoardBuffer boardBuffer = new BoardBuffer(10);
        GameOverCondition[] gameOverConditions = {new AllDeadCondition()
                , new StuckCondition()
                , new RepeatPatternCondition(boardBuffer)};
        Application gameOfLife = new Application(userListener,settingsStorage,automaton,gameOverConditions);
        gameOfLife.run();
    }
}