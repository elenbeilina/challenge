package org.app.connector;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class RandomSourceConnector extends SourceConnector {

    public static final String VERSION = "1.O.O";
    private RandomSourceConnectorConfig config;

    public Class<? extends Task> taskClass() {
        return RandomSourceTask.class;
    }

    public void stop() {
        log.info("{} Stopping RandomSourceConnector.", this);
    }

    public ConfigDef config() {
        return RandomSourceConnectorConfig.conf();
    }

    public String version() {
        return VERSION;
    }

    public void start(Map<String, String> props) {
        log.info("{} Starting RandomSourceConnector", this);
        try {
            config = new RandomSourceConnectorConfig(props);
        } catch (ConfigException e) {
            throw new ConnectException("Couldn't start RandomSourceConnector due to configuration error", e);
        }
    }

    public List<Map<String, String>> taskConfigs(int maxTasks) {
        if (maxTasks != 1) {
            log.info("Ignoring maxTasks as there can only be one.");
        }
        return Collections.singletonList(config.originalsStrings());

    }
}
