package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();
    private static String reportDir;

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
            // Create timestamped report folder
            String baseDir = props.getProperty("report.dir", "reports");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            reportDir = baseDir + "/run_" + timestamp;
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    /** Return raw property value, or null if not found */
    public static String get(String key) {
        return props.getProperty(key);
    }

    /** Return boolean property (default false if missing or invalid) */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    /** Return property with default fallback */
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    /** Return per-run report directory */
    public static String getReportDir() {
        return reportDir;
    }

}
