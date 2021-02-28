package org.app.connector;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class RestSinkConnector extends SinkConnector {

    public static final String VERSION = "1.O.O";
    private RestSinkConnectorConfig config;

    public Class<? extends Task> taskClass() {
        return RestSinkTask.class;
    }

    public ConfigDef config() {
        return RestSinkConnectorConfig.conf();
    }

    public void stop() {
        log.info("{} Stopping RestSinkConnector.", this);
    }

    public String version() {
        return VERSION;
    }

    public void start(Map<String, String> props) {
        log.info("{} Starting RestSinkConnector", this);
        try {
            config = new RestSinkConnectorConfig(props);

        } catch (
                ConfigException e) {
            throw new ConnectException("Couldn't start RestSinkConnector due to configuration error", e);
        }
    }

    /**
     * Method fo defining tasks scale and what configuration each task should have.
     *
     * @param maxTasks -  how many tasks (workers) needs connector across cluster
     * @return - a list of a single configuration map, because our connector only scales up to one task
     */
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        if (maxTasks != 1) {
            log.info("Ignoring maxTasks as there can only be one.");
        }
        return Collections.singletonList(config.originalsStrings());
    }
}
