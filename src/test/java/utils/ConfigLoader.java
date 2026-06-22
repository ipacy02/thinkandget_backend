package utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyLoader.propertyLoader("src/test/resources/config.properties");
    }

    public static synchronized ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("base_url");
        if (prop != null) return prop;
        throw new RuntimeException("Property 'base_url' is missing in config.properties");
    }

    public String getBasePath() {
        String prop = properties.getProperty("base_path");
        if (prop != null) return prop;
        throw new RuntimeException("Property 'base_path' is missing in config.properties");
    }

}