package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

import static org.apache.kafka.common.config.ConfigDef.Importance.HIGH;
import static org.apache.kafka.common.config.ConfigDef.NO_DEFAULT_VALUE;

public class RestSinkConnectorConfig extends AbstractConfig {

    static final String SINK_URI = "api.uri";
    private static final String SINK_URI_DOC = "The uri for REST sink connector.";

    private static final String MAX_RETRIES = "max.retries";
    private static final String MAX_RETRIES_DOC = "Number of times to retry request in case of failure. " +
            "Negative means infinite number of retries";
    private static final int MAX_RETRIES_DEFAULT = 5;

    private static final String EXECUTOR = "http.executor.class";
    private static final String EXECUTOR_DOC = "HTTP request executor. Default is OkHttpRequestExecutor";
    private static final String EXECUTOR_DEFAULT = "org.app.connector.OkHttpRequestExecutor";

    private final OkHttpRequestExecutor executor;

    public RestSinkConnectorConfig(Map<String, String> parsedConfig) {
        super(conf(), parsedConfig);

        executor = this.getConfiguredInstance(EXECUTOR, OkHttpRequestExecutor.class);
    }

    /**
     * Method for generating configuration that is required for sink connector.
     *
     * @return - configuration that manipulates sink connector.
     */
    public static ConfigDef conf() {
        return new ConfigDef()
                .define(SINK_URI,
                        ConfigDef.Type.STRING,
                        NO_DEFAULT_VALUE,
                        HIGH,
                        SINK_URI_DOC)
                .define(MAX_RETRIES,
                        ConfigDef.Type.INT,
                        MAX_RETRIES_DEFAULT,
                        ConfigDef.Importance.LOW,
                        MAX_RETRIES_DOC)
                .define(EXECUTOR,
                        ConfigDef.Type.CLASS,
                        EXECUTOR_DEFAULT,
                        ConfigDef.Importance.LOW,
                        EXECUTOR_DOC)
                ;
    }

    public int getMaxRetries() {
        return this.getInt(MAX_RETRIES);
    }

    public String getUrl() {
        return this.getString(SINK_URI);
    }

    public OkHttpRequestExecutor getExecutor() {
        return executor;
    }
}
