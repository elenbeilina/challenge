package org.app.connector;

import com.github.javafaker.Faker;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RandomSourceTask extends SourceTask {

    private static final Logger log = LoggerFactory.getLogger(RandomSourceTask.class);
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

    public List<SourceRecord> poll() throws InterruptedException {
        final ArrayList<SourceRecord> records = new ArrayList<>();
        // TODO your implementation goes here
        return records;
    }
}



