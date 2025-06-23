package me.pajic.experimentalist.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ModConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("Experimentalist-Config");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configFile = FMLPaths.CONFIGDIR.get().resolve("experimentalist.json");
    public static Config CONFIG;

    public static class Config {
        public boolean tradeRebalance = false;
        //? if <= 1.21.1
        public boolean bundles = false;
        //? if > 1.21.1 {
        /*public boolean redstoneExperiments = false;
        public boolean minecartImprovements = false;
        *///?}
        //? if > 1.21.1 < 1.21.4
        /*public boolean winterDrop = false;*/
    }

    public static void loadConfig() {
        readConfig();
        saveConfig();
    }

    private static void readConfig() {
        try (FileReader reader = new FileReader(configFile.toFile())) {
            CONFIG = GSON.fromJson(reader, Config.class);
        } catch (FileNotFoundException | JsonSyntaxException e) {
            LOGGER.warn("Config doesn't exist or is malformed, initializing new mod config...");
            initializeConfig();
        } catch (IOException e) {
            LOGGER.error("Failed to read mod config", e);
        }
    }

    private static void initializeConfig() {
        try (FileWriter writer = new FileWriter(configFile.toFile())) {
            CONFIG = new Config();
            GSON.toJson(CONFIG, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to initialize mod config", e);
        }
    }

    private static void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile.toFile())) {
            GSON.toJson(CONFIG, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save mod config", e);
        }
    }
}
