package me.pajic.experimentalist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagRegistry;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ModConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("Experimentalist");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE_PATH = FMLPaths.CONFIGDIR.get().resolve("experimentalist.json");
    public static Map<String, Boolean> FEATURES = new HashMap<>();

    public static void loadConfig() {
        readConfig();
        saveConfig();
    }

    public static void initializeConfig(FeatureFlagRegistry registry) {
        Set<String> names = registry.toNames(registry.allFlags()).stream()
                .map(ResourceLocation::getPath)
                .filter(path -> !path.equals("vanilla"))
                .collect(Collectors.toSet());
        if (FEATURES.isEmpty()) {
            LOGGER.info("[Experimentalist] Initializing config with features.");
            names.forEach(name -> FEATURES.put(name, false));
            saveConfig();
        }
        if (!names.containsAll(FEATURES.keySet())) {
            LOGGER.warn("[Experimentalist] Removing unknown features from config.");
            FEATURES.keySet().removeIf(name -> !names.contains(name));
            saveConfig();
        } else if (!FEATURES.keySet().containsAll(names)) {
            LOGGER.warn("[Experimentalist] Adding missing features to config.");
            names.forEach(name -> { if (!FEATURES.containsKey(name)) FEATURES.put(name, false); });
            saveConfig();
        }
    }

    private static void readConfig() {
        try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
            //noinspection Convert2Diamond
            FEATURES = GSON.fromJson(reader, new TypeToken<Map<String, Boolean>>(){});
        } catch (FileNotFoundException | JsonSyntaxException e) {
            LOGGER.warn("Config doesn't exist or is malformed, initializing config file...");
            saveConfig();
        } catch (IOException e) {
            LOGGER.error("Failed to read mod config", e);
        }
    }

    private static void saveConfig() {
        try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
            GSON.toJson(FEATURES, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save mod config", e);
        }
    }
}