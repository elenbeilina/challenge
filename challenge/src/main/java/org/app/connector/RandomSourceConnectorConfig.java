package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

import static org.apache.kafka.common.config.ConfigDef.Importance.HIGH;
import static org.apache.kafka.common.config.ConfigDef.Importance.LOW;

public class RandomSourceConnectorConfig extends AbstractConfig {

    private static final String SOURCE_TOPIC_CONFIG = "topic";
    private static final String SOURCE_TOPIC_DOC = "Topic for publishing Star Trek characters.";

    private static final String SOURCE_INTERVAL = "interval";
    private static final int SOURCE_INTERVAL_DEFAULT = 1000;
    private static final String SOURCE_INTERVAL_DOC = "Interval in milliseconds.";

    public RandomSourceConnectorConfig(final Map<?, ?> parsedConfig) {
        super(conf(), parsedConfig);
    }

    /**
     * Method for generating configuration that is required for source connector.
     *
     * @return - configuration that manipulates source connector.
     */
    public static ConfigDef conf() {
        return new ConfigDef()
                .define(SOURCE_TOPIC_CONFIG,
                        ConfigDef.Type.STRING,
                        ConfigDef.NO_DEFAULT_VALUE,
                        HIGH,
                        SOURCE_TOPIC_DOC)
                .define(SOURCE_INTERVAL,
                        ConfigDef.Type.INT,
                        SOURCE_INTERVAL_DEFAULT,
                        LOW,
                        SOURCE_INTERVAL_DOC)
                ;
    }

    public String getTopic() {
        return this.getString(SOURCE_TOPIC_CONFIG);
    }

    public int getInterval() {
        return this.getInt(SOURCE_INTERVAL);
    }

}
