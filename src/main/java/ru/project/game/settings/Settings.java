package ru.project.game.settings;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Settings {
    private int rows;
    private int cols;
    private FirstGenerationSettings firstGeneration;

    public static Settings getDefaultSettings(){
        Settings settings = new Settings();
        settings.setCols(5);
        settings.setRows(5);
        settings.setFirstGeneration(FirstGenerationSettings.getDefaultFirstGenerationSettings());
        return settings;
    }

}
