package org.app.connector;

import org.apache.kafka.connect.errors.ConnectException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestSinkConnectorTest {

    @Mock
    private RestSinkConnectorConfig config;
    @InjectMocks
    private RestSinkConnector connector;

    private final Map<String, String> DEFAULT_PROPS = Collections.singletonMap("api.uri", "test");

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
        assert config.containsKey("api.uri");
    }
}