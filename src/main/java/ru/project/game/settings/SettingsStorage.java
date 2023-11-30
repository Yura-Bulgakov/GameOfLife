package ru.project.game.settings;

import java.io.IOException;

public interface SettingsStorage {
    void save(Settings settings)throws IOException;
    Settings load()throws IOException;

}
