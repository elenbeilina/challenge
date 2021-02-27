package org.app.connector;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;
import static org.app.connector.RandomSourceConnectorConfig.INTERVAL;
import static org.app.connector.RandomSourceConnectorConfig.TOPIC_CONFIG;

@Slf4j
public class RandomSourceTask extends SourceTask {

    public RandomSourceConnectorConfig config;
    private Faker faker;

    private long lastExecution;
    private List<ItemValue> values;

    public String version() {
        return RandomSourceConnector.VERSION;
    }

    public void stop() {
        log.info("{} Stopping RandomSourceTask.", this);
    }

    public void start(Map<String, String> props) {
        config = new RandomSourceConnectorConfig(props);
        faker = new Faker();
    }

    public List<SourceRecord> poll() {
        ItemValue value = ItemValue.newBuilder().setValue(faker.starTrek().character()).build();
        values.add(value);

        if (System.currentTimeMillis() > (lastExecution + config.getConfiguredInstance(INTERVAL, Integer.class))) {
            lastExecution = System.currentTimeMillis();

            List<SourceRecord> sourceRecords = values.stream()
                    .map(this::buildSourceRecord)
                    .collect(Collectors.toList());

            values = new ArrayList<>();

            return sourceRecords;
        }
        return Collections.emptyList();
    }

    private SourceRecord buildSourceRecord(ItemValue value) {
        Map<String, Object> sourceOffset = Collections.singletonMap("timestamp", currentTimeMillis());
        Map<String, Object> sourcePartition = Collections.singletonMap("value", "starTrek");
        return new SourceRecord(sourcePartition, sourceOffset, config.getConfiguredInstance(TOPIC_CONFIG, String.class), Schema.STRING_SCHEMA, value.getValue());
    }
}



