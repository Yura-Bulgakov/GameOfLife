package ru.project.game.settings;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import ru.project.board.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class YamlSettingsStorage implements SettingsStorage {
    private final Yaml yaml;
    private final String filename;


    public YamlSettingsStorage(String filename) {
        this.filename = filename;
        Constructor constructor = new Constructor(Settings.class);
        TypeDescription customTypeDescription = new TypeDescription(Settings.class);
        customTypeDescription.addPropertyParameters("FirstGenerationSettings", FirstGenerationSettings.class);
//        customTypeDescription.addPropertyParameters("Coordinate", Coordinate.class);
        constructor.addTypeDescription(customTypeDescription);
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        this.yaml = new Yaml(new Constructor(Settings.class), new Representer(),options);
//        this.yaml = new Yaml();
    }

    @Override
    public void save(Settings settings)throws IOException {
        String data = yaml.dump(settings);
        PrintWriter pw = new PrintWriter(this.filename);
        pw.print(data);
        pw.close();
    }
    @Override
    public Settings load()throws IOException{
        Path path = Paths.get(this.filename);
        InputStream inputStream = Files.newInputStream(path);
        return yaml.load(inputStream);
    }
}
