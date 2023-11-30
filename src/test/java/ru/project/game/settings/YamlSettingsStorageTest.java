package ru.project.game.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class YamlSettingsStorageTest {
YamlSettingsStorage yamlHelper;
String filename;

    @BeforeEach
    void setUp() {
        filename = "test-settings.yaml";
        yamlHelper = new YamlSettingsStorage(filename);
    }

    @Test
    void saveAndLoadTest() throws IOException {
        Settings originalSettings = Settings.getDefaultSettings();
        yamlHelper.save(originalSettings);
        Settings loadedSettings = yamlHelper.load();

        assertEquals(originalSettings.getRows(), loadedSettings.getRows());
        assertEquals(originalSettings.getCols(), loadedSettings.getCols());
        assertEquals(originalSettings.getFirstGeneration().isRandom(), loadedSettings.getFirstGeneration().isRandom());

        Files.deleteIfExists(Path.of(filename));
    }

}