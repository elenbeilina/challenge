package org.app.connector;

import org.apache.kafka.connect.errors.ConnectException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.app.connector.RandomSourceConnectorConfig.INTERVAL;
import static org.app.connector.RandomSourceConnectorConfig.TOPIC_CONFIG;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomSourceConnectorTest {
    @Mock
    private RandomSourceConnectorConfig config;
    @InjectMocks
    private RandomSourceConnector connector;

    private final Map<String,String> DEFAULT_PROPS = Collections.singletonMap(TOPIC_CONFIG, "main");

    @Test
    void failStart() {
        assertThrows(ConnectException.class, () -> connector.start(Collections.emptyMap()));
    }

    @Test
    void start() {
        assertDoesNotThrow(() -> connector.start(DEFAULT_PROPS));
    }

    @Test
    void taskConfigs() {
        when(config.originalsStrings()).thenReturn(DEFAULT_PROPS);

        List<Map<String, String>> configs = connector.taskConfigs(2);

        assert configs.size() == 1;

        Map<String, String> config = configs.get(0);

        assert config.size() == 1;
        assert config.containsKey(TOPIC_CONFIG);
    }
}