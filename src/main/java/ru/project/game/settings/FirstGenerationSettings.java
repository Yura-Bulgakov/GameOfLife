package ru.project.game.settings;

import lombok.Getter;
import lombok.Setter;
import ru.project.board.Coordinate;

@Setter
@Getter
public class FirstGenerationSettings {
    private boolean random;
    private Coordinate[] coordinates;

    public static FirstGenerationSettings getDefaultFirstGenerationSettings(){
        FirstGenerationSettings settings = new FirstGenerationSettings();
        settings.setRandom(true);
        return settings;
    }
}
