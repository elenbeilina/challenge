package org.app.connector;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class RestSinkTask extends SinkTask {

    private static final Logger log = LoggerFactory.getLogger(RestSinkTask.class);
    public RestSinkConnectorConfig config;

    public void start(Map<String, String> props) {
        config = new RestSinkConnectorConfig(props);
    }

    public void stop() {
        log.info("{} Stopping RestSinkTask.", this);
    }

    public String version() {
        return RestSinkConnector.VERSION;
    }

    public void put(Collection<SinkRecord> collection) {
        for (SinkRecord sinkRecord : collection) {
            // TODO: your implementation goes here
        }
    }

}



