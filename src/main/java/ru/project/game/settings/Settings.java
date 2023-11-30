package ru.project.game.settings;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Settings {
    private int rows;
    private int cols;
    private int delay;
    private FirstGenerationSettings firstGeneration;

    public static Settings getDefaultSettings(){
        Settings settings = new Settings();
        settings.setCols(60);
        settings.setRows(30);
        settings.setDelay(100);
        settings.setFirstGeneration(FirstGenerationSettings.getDefaultFirstGenerationSettings());
        return settings;
    }

}
