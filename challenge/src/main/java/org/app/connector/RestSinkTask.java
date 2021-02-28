package org.app.connector;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class RestSinkTask extends SinkTask {

    private RestSinkConnectorConfig config;
    private OkHttpRequestExecutor executor;

    public void start(Map<String, String> props) {
        config = new RestSinkConnectorConfig(props);
        executor = config.getExecutor();
    }

    public void stop() {
        log.info("{} Stopping RestSinkTask.", this);
    }

    public String version() {
        return RestSinkConnector.VERSION;
    }

    /**
     * Method that executes post request to external api
     *
     * @param collection - batch of messages from topic
     */
    @SneakyThrows
    public void put(Collection<SinkRecord> collection) {

        if (collection.isEmpty()) {
            log.debug("Empty record collection to process");
            return;
        }

        for (SinkRecord record : collection) {
            int retries = 0;

            Request request = new Request.Builder()
                    .url(config.getUrl())
                    .post(new FormBody.Builder().add("value", record.value().toString()).build())
                    .build();

            while (retries < config.getMaxRetries()) {
                if (executor.execute(request)) {
                    break;
                }
                retries++;
            }
        }
    }

}



