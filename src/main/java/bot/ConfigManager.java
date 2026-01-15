package bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final String FILE_NAME;
    private final Gson gson = new Gson();
    private Map<Long, Long> configMap = new HashMap<>();

    public ConfigManager(String fileName) {
        this.FILE_NAME = fileName;
        loadChannels();
    }

    private void loadChannels() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<Long, Long>>() {}.getType();
            configMap = gson.fromJson(reader, type);
            if (configMap == null) {
                configMap = new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(configMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEntry(long guildId, long channelId) {
        configMap.put(guildId, channelId);
        save();
    }

    public Long getEntry(long guildId) {
        return configMap.get(guildId);
    }

    public boolean containsEntry(long guildID) {
        return configMap.containsKey(guildID);
    }
}
