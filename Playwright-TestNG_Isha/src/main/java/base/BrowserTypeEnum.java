/**
 * Enum representing supported browser types in the framework.
 * Used to standardize browser selection and avoid hardcoding strings.
 */

package base;
import utils.ConfigManager;

/**
 * Enum for supported browser types.
 * Provides conversion utilities and config-based initialization.
 */

public enum BrowserTypeEnum {
    CHROME("chrome"),
    FIREFOX("firefox"),
    WEBKIT("webkit"),
    MSEDGE("msedge");

    private final String name;

    /**
     * Constructs a BrowserTypeEnum with its associated string name.
     *
     * @param name browser name (e.g., "chrome", "firefox")
     */
    BrowserTypeEnum(String name) {
        this.name = name;
    }
    
    /**
     * Returns the lowercase browser name.
     *
     * @return the browser name string
     */
    public String getName() {
        return name;
    }

    
    /**
     * Converts a string into the corresponding enum value.
     * If no match is found, defaults to {@link #CHROME}.
     *
     * @param value the string value to match (case-insensitive)
     * @return the matching BrowserTypeEnum
     */
    // 🔹 Conversion from string to enum
    public static BrowserTypeEnum fromString(String value) {
        for (BrowserTypeEnum type : BrowserTypeEnum.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return CHROME; // default
    }

    /**
     * Reads the browser type directly from config.properties.
     * Uses {@link utils.ConfigManager#get(String)} with key "browser".
     *
     * @return the BrowserTypeEnum from config file
     */
    // 🔹 Factory method: reads directly from config.properties
    public static BrowserTypeEnum fromConfig() {
        String browserName = ConfigManager.get("browser");
        return fromString(browserName);
    }
}
