package ru.project.board.factory;

import ru.project.board.Coordinate;
import ru.project.game.settings.Settings;

public class SettingsBoardFactoryFactory implements BoardFactoryFactory{
    Settings settings;

    public SettingsBoardFactoryFactory(Settings settings) throws BoardFactoryException {
        if (settings == null){
            throw new BoardFactoryException("Не заданы настройки");
        }
        this.settings = settings;
    }

    @Override
    public BoardFactory createBoardFactory() throws BoardFactoryException {
        return selectBoardFactoryByRandomFlag(settings);
    }

    private BoardFactory selectBoardFactoryByRandomFlag(Settings settings) throws BoardFactoryException {
        if (settings.getFirstGeneration().isRandom()){
            return new RandomBoardFactory(settings.getRows(),settings.getCols());
        }else {
            Coordinate[] coordinates = settings.getFirstGeneration().getCoordinates();
            if (coordinates == null) {
                coordinates = new Coordinate[0];
            }
            return new CoordinateBoardFactory(settings.getRows()
                    ,settings.getCols()
                    ,coordinates);
        }
    }
}
