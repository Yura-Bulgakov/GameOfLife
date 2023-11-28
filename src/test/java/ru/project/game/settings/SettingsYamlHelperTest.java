package ru.project.game.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.automaton.GameOfLife;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SettingsYamlHelperTest {
SettingsYamlHelper yamlHelper;
String filename;

    @BeforeEach
    void setUp() {
        yamlHelper = new SettingsYamlHelper();
        filename = "test-settings.yaml";
    }

    @Test
    void saveAndLoadTest() throws IOException {
        Settings originalSettings = Settings.getDefaultSettings();
        yamlHelper.save(originalSettings, filename);
        Settings loadedSettings = yamlHelper.load(filename);

        assertEquals(originalSettings.getRows(), loadedSettings.getRows());
        assertEquals(originalSettings.getCols(), loadedSettings.getCols());
        assertEquals(originalSettings.getFirstGeneration().isRandom(), loadedSettings.getFirstGeneration().isRandom());

        Files.deleteIfExists(Path.of(filename));
    }

}