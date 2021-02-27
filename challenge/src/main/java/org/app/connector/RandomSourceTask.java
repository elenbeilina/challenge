package org.app.connector;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static org.app.connector.RandomSourceConnectorConfig.INTERVAL;
import static org.app.connector.RandomSourceConnectorConfig.TOPIC_CONFIG;

@Slf4j
public class RandomSourceTask extends SourceTask {

    public RandomSourceConnectorConfig config;
    private Faker faker;

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

    /**
     * Kafka is calling that method in loop as quickly as it has capacity for.
     * Here we should be making the calls to the external system.
     * This particularly method generates Star Track characters for the Connect framework.
     * @return - returning a list of SourceRecords.
     */
    @SneakyThrows
    public List<SourceRecord> poll() {
        SourceRecord sourceRecord = buildSourceRecord(ItemValue.newBuilder()
                .setValue(faker.starTrek().character())
                .build());

        Thread.sleep(config.getInt(INTERVAL));

        return Collections.singletonList(sourceRecord);
    }

    /**
     * Method for building source record using AvroConverter
     * @see AvroConverter
     * @param value - Item value, that we want to send to kafka topic
     * @return - source value
     */
    private SourceRecord buildSourceRecord(ItemValue value) {
        Map<String, Object> sourceOffset = Collections.singletonMap("timestamp", currentTimeMillis());
        Map<String, Object> sourcePartition = Collections.singletonMap("value", value.getValue());

        SchemaAndValue schemaAndValue = new AvroConverter<ItemValue>().getSchemaAndValue(value);

        return new SourceRecord(sourcePartition, sourceOffset, config.getString(TOPIC_CONFIG),
                schemaAndValue.schema(), schemaAndValue.value());
    }
}



