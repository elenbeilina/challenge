package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

import static org.apache.kafka.common.config.ConfigDef.Importance.HIGH;

public class RandomSourceConnectorConfig extends AbstractConfig {

    public static final String TOPIC_CONFIG = "topic";
    private static final String TOPIC_DEFAULT = "main";
    private static final String TOPIC_DOC = "Topic for publishing Star Trek characters.";

    public static final String INTERVAL = "interval";
    private static final int INTERVAL_DEFAULT = 1000;
    private static final String INTERVAL_DOC = "Interval in milliseconds.";

    public RandomSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public RandomSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
                .define(TOPIC_CONFIG, ConfigDef.Type.STRING, TOPIC_DEFAULT,
                        new ConfigDef.NonEmptyStringWithoutControlChars(), HIGH, TOPIC_DOC)
                .define(INTERVAL, ConfigDef.Type.INT, INTERVAL_DEFAULT,
                        ConfigDef.Importance.LOW, INTERVAL_DOC)
                ;
    }
}
