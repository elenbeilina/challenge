package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

public class OkHttpRequestExecutorConfig extends AbstractConfig {

    public static final String TIMEOUT = "connection.timeout";
    private static final String TIMEOUT_DOC = "HTTP connection timeout in milliseconds";
    private static final long TIMEOUT_DEFAULT = 2000;

    public static final String READ_TIMEOUT = "read.timeout";
    private static final String READ_TIMEOUT_DOC = "HTTP read timeout in milliseconds";
    private static final long READ_TIMEOUT_DEFAULT = 2000;

    public static final String KEEP_ALIVE_DURATION = "keep.alive.ms";
    private static final String KEEP_ALIVE_DURATION_DOC = "For how long keep HTTP connection should be alive in milliseconds";
    private static final long KEEP_ALIVE_DURATION_DEFAULT = 300000;

    public static final String MAX_IDLE_CONNECTION = "max.idle";
    private static final String MAX_IDLE_CONNECTION_DOC = "How many idle connections per host can be opened";
    private static final int MAX_IDLE_CONNECTION_DEFAULT = 5;


    public OkHttpRequestExecutorConfig(Map<String, ?> parsedConfig) {
        super(conf(), parsedConfig);
    }

    /**
     * Method for generating configuration that is required for Ok Http Request Executor.
     *
     * @return - configuration that manipulates Ok Http Request Executor.
     */
    public static ConfigDef conf() {
        return new ConfigDef()
                .define(TIMEOUT,
                        Type.LONG,
                        TIMEOUT_DEFAULT,
                        Importance.LOW,
                        TIMEOUT_DOC)
                .define(READ_TIMEOUT,
                        Type.LONG,
                        READ_TIMEOUT_DEFAULT,
                        Importance.LOW,
                        READ_TIMEOUT_DOC)
                .define(KEEP_ALIVE_DURATION,
                        Type.LONG,
                        KEEP_ALIVE_DURATION_DEFAULT,
                        Importance.LOW,
                        KEEP_ALIVE_DURATION_DOC)

                .define(MAX_IDLE_CONNECTION,
                        Type.INT,
                        MAX_IDLE_CONNECTION_DEFAULT,
                        Importance.LOW,
                        MAX_IDLE_CONNECTION_DOC)
                ;
    }

    public long getReadTimeout() {
        return this.getLong(READ_TIMEOUT);
    }

    public long getConnectionTimeout() {
        return this.getLong(TIMEOUT);
    }

    public long getKeepAliveDuration() {
        return this.getLong(KEEP_ALIVE_DURATION);
    }

    public int getMaxIdleConnections() {
        return this.getInt(MAX_IDLE_CONNECTION);
    }
}
