package ru.project.game.settings;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SettingsYamlHelper {
    private final Yaml yaml;
    public SettingsYamlHelper() {
        Constructor constructor = new Constructor(Settings.class);
        TypeDescription customTypeDescription = new TypeDescription(Settings.class);
        customTypeDescription.addPropertyParameters("FirstGenerationSettings", FirstGenerationSettings.class);
        constructor.addTypeDescription(customTypeDescription);
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        this.yaml = new Yaml(constructor, new Representer(),options);
    }

    public void save(Settings settings, String filename)throws IOException {
        String data = yaml.dump(settings);
        PrintWriter pw = new PrintWriter(filename);
        pw.print(data);
        pw.close();
    }
    public Settings load(String filename)throws IOException{
        Path path = Paths.get(filename);
        InputStream inputStream = Files.newInputStream(path);
        return yaml.load(inputStream);
    }
}
