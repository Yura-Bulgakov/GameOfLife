package ru.project.game;

import ru.project.automaton.AutomationException;
import ru.project.automaton.CellularAutomaton;
import ru.project.board.Board;
import ru.project.board.Coordinate;
import ru.project.board.factory.BoardFactoryException;
import ru.project.board.factory.BoardFactoryFactory;
import ru.project.board.factory.SettingsBoardFactoryFactory;
import ru.project.game.menu.MainMenu;
import ru.project.game.menu.Menuable;
import ru.project.game.menu.PopulationMenu;
import ru.project.game.menu.SettingsMenu;
import ru.project.game.settings.FirstGenerationSettings;
import ru.project.game.settings.Settings;
import ru.project.game.settings.SettingsStorage;
import ru.project.game.stop.GameOverCondition;
import ru.project.ui.UserListener;

import java.io.IOException;
import java.util.Arrays;

public class Application {
    private CellularAutomaton automaton;
    private GameOverCondition[] gameOverCheckers;
    private UserListener userListener;
    private BoardFactoryFactory boardFactoryFactory;
    private Settings settings;
    private SettingsStorage settingsStorage;

    public Application(UserListener userListener,
                       SettingsStorage settingsStorage,
                       CellularAutomaton cellularAutomaton,
                        GameOverCondition[] gameOverCheckers) throws IOException, BoardFactoryException {
        this.userListener = userListener;
        this.settingsStorage = settingsStorage;
        try {
            this.settings = this.settingsStorage.load();
        } catch (IOException e) {
            this.settings = Settings.getDefaultSettings();
        }
        if (this.settings.getFirstGeneration() == null) {
            this.settings.setFirstGeneration(FirstGenerationSettings.getDefaultFirstGenerationSettings());
        }
        this.automaton = cellularAutomaton;
        this.gameOverCheckers = gameOverCheckers;
        this.boardFactoryFactory = new SettingsBoardFactoryFactory(this.settings);
    }

    public void run() throws IOException, InterruptedException {
        chooseMainMenuItem();
    }

    private <T extends Enum<T> & Menuable> T pickApplicationMenuItem(String prompt, Class<T> menuItems) {
        T[] menuElements = menuItems.getEnumConstants();
        String[] menuDescriptions = Arrays.stream(menuElements)
                .map(Menuable::getDescription)
                .toArray(String[]::new);
        String choice = userListener.pickMenuItem(prompt, menuDescriptions);
        T selectedItem = Arrays.stream(menuElements)
                .filter(item -> item.getDescription().equals(choice))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Неизвестная ошибка, не удалось определить пункт меню: " + choice));
        return selectedItem;
    }

    private void chooseMainMenuItem() throws IOException, InterruptedException {
        while (true) {
            MainMenu menu = pickApplicationMenuItem(MainMenu.menuDescription, MainMenu.class);
            switch (menu) {
                case START_GAME: {
                    Board board = null;
                    try {
                        board = boardFactoryFactory.createBoardFactory().createBoard();
                    } catch (BoardFactoryException e) {
                        throw new RuntimeException(e);
                    }
                    startGame(board);
                    break;
                }
                case SET_SETTINGS: {
                    chooseSettingsMenuItem();
                    break;
                }
                case SHOW_SETTINGS: {
                    showSettings();
                    break;
                }
                case EXIT: {
                    return;
                }
            }
        }
    }

    private void showSettings() {
        boolean hasCellAmount = settings.getFirstGeneration().getCellAmount() > 0;
        boolean random = settings.getFirstGeneration().isRandom();
        StringBuilder message = new StringBuilder(String.format("Размеры поля %d x %d \n",
                settings.getRows(), settings.getCols()));
        if (random) {
            if (!hasCellAmount) {
                message.append("Случайное количество случайных клеток в начальной популяции\n");
            } else {
                message.append(String.format("%d случайных клеток в начальной популяции\n",
                        settings.getFirstGeneration().getCellAmount()));
            }
        } else {
            message.append("Клетки начальной популяции: \n");
            for (Coordinate coordinate : settings.getFirstGeneration().getCoordinates()) {
                message.append(String.format("Строка: %d Столбец: %d \n",coordinate.getRow(),coordinate.getCol()));
            }
        }
        message.append(String.format("Задержка вывода: %d миллисекунд \n", settings.getDelay()));
        userListener.showMessage(message.toString());
    }


    private void chooseSettingsMenuItem() throws IOException, InterruptedException {
        while (true) {
            SettingsMenu menu = pickApplicationMenuItem(SettingsMenu.menuDescription, SettingsMenu.class);
            switch (menu) {
                case SET_FIELD_SIZE: {
                    setFieldSize();
                    break;
                }
                case SET_INITIAL_POPULATION: {
                    choosePopulationMenuItem();
                    break;
                }
                case SET_DELAY: {
                    setDelay();
                    break;
                }
                case BACK: {
                    return;
                }
            }
        }
    }

    private void setDelay() {
        settings.setDelay(userListener.getInt("Введите задержку вывода в миллисекундах: "));
        save();
    }

    private void setFieldSize() throws IOException {
        settings.setRows(userListener.getInt("Введите количество строк: "));
        settings.setCols(userListener.getInt("Введите количество столбцов: "));
        save();
    }


    private void choosePopulationMenuItem() throws IOException, InterruptedException {
        while (true) {
            PopulationMenu menu = pickApplicationMenuItem(PopulationMenu.menuDescription, PopulationMenu.class);
            switch (menu) {
                case RANDOM: {
                    setInitialGenerationRandomSetting();
                    break;
                }
                case RANDOM_SET_AMOUNT: {
                    setInitialGenerationRandomCellAmount();
                    break;
                }
                case ENTER_COORDINATES: {
                    setInitialGenerationCellCoordinates();
                    break;
                }
                case BACK: {
                    return;
                }
            }
        }
    }

    private void setInitialGenerationRandomSetting() throws IOException {
        if (settings.getFirstGeneration().isRandom()) {
            settings.getFirstGeneration().setRandom(false);
            userListener.showMessage("Распределение живых клеток в первом поколении вручную");
        } else {
            settings.getFirstGeneration().setRandom(true);
            userListener.showMessage("Распределение живых клеток в первом поколении установлено на случайное");
        }
        save();
    }

    private void setInitialGenerationRandomCellAmount() throws IOException {
        settings.getFirstGeneration().setCellAmount(userListener.getInt("Введите число клеток"));
        save();
    }

    private void setInitialGenerationCellCoordinates() {
        int count = userListener.getInt("Введите количество клеток:");
        Coordinate[] coordinates = new Coordinate[count];
        for (int i = 0; i < count; i++) {
            coordinates[i] = new Coordinate(userListener.getInt(String.format("Введите номер строки для клетки %d", i))
                    , userListener.getInt(String.format("Введите номер столбца для клетки %d", i)));
        }
        settings.getFirstGeneration().setCoordinates(coordinates);
        save();
    }


    private void startGame(Board board) throws InterruptedException {
        long iteration = 0;

        while (true) {
            userListener.showMessage("Ход: " + iteration);
            userListener.showBoard(board);
            for (GameOverCondition gameOverChecker : gameOverCheckers) {
                if (gameOverChecker.isGameOver(board)) {
                    userListener.showMessage("Конец игры!");
                    return;
                }
            }
            try {
                board = automaton.makeMove(board);
            } catch (AutomationException e) {
                throw new RuntimeException(e);
            }
            iteration++;
            Thread.sleep(settings.getDelay());
        }
    }

    private void save() {
        try {
            settingsStorage.save(this.settings);
        } catch (IOException ignored) {
        }
    }


}
