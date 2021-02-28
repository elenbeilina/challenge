package org.app.connector;

import com.github.javafaker.Faker;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RandomSourceTaskTest {

    @Spy
    private Faker faker;
    @Spy
    private final RandomSourceConnectorConfig config =
            new RandomSourceConnectorConfig(Collections.singletonMap("topic", "test"));
    @InjectMocks
    private RandomSourceTask randomSourceTask;

    @Test
    void poll() {
        List<SourceRecord> records = randomSourceTask.poll();

        assert records.size() == 1;
        assertEquals(records.get(0).topic(), "test");
        verify(faker,times(1)).starTrek();
    }
}