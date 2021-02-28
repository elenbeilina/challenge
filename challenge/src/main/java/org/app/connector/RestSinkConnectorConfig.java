package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Collections;
import java.util.Map;

import static org.apache.kafka.common.config.ConfigDef.Importance.HIGH;
import static org.apache.kafka.common.config.ConfigDef.NO_DEFAULT_VALUE;

public class RestSinkConnectorConfig extends AbstractConfig {

    static final String SINK_METHOD = "rest.method";
    private static final String SINK_METHOD_DOC = "The HTTP method for REST sink connector.";
    private static final String SINK_METHOD_DEFAULT = "POST";

    static final String SINK_HEADERS_LIST = "rest.headers";
    private static final String SINK_HEADERS_LIST_DOC = "The request headers for REST sink connector.";

    static final String SINK_URI = "api.uri";
    private static final String SINK_URI_DOC = "The uri for REST sink connector.";

    private static final String MAX_RETRIES = "max.retries";
    private static final String MAX_RETRIES_DOC = "Number of times to retry request in case of failure. " +
            "Negative means infinite number of retries";
    private static final int MAX_RETRIES_DEFAULT = -1;

    private static final String HTTP_CODES_WHITELIST = "http.codes.whitelist";
    private static final String HTTP_CODES_WHITELIST_DOC = "HTTP codes which are considered as successful. "
            + "Request will be retried if response code from the server does not match the regex";
    private static final String HTTP_CODES_WHITELIST_DEFAULT = "^[2-4]{1}\\d{1}\\d{1}$";

    public RestSinkConnectorConfig(Map<String, String> parsedConfig) {
        super(conf(), parsedConfig);
    }

    /**
     * Method for generating configuration that is required for sink connector.
     *
     * @return - configuration that manipulates sink connector.
     */
    public static ConfigDef conf() {
        return new ConfigDef()
                .define(SINK_METHOD,
                        ConfigDef.Type.STRING,
                        SINK_METHOD_DEFAULT,
                        HIGH,
                        SINK_METHOD_DOC)
                .define(SINK_HEADERS_LIST,
                        ConfigDef.Type.LIST,
                        Collections.EMPTY_LIST,
                        HIGH,
                        SINK_HEADERS_LIST_DOC)
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
                .define(HTTP_CODES_WHITELIST,
                        ConfigDef.Type.STRING,
                        HTTP_CODES_WHITELIST_DEFAULT,
                        ConfigDef.Importance.LOW,
                        HTTP_CODES_WHITELIST_DOC)
                ;
    }


}
