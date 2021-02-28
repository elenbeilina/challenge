package org.app.connector;

import org.apache.kafka.common.config.ConfigException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomSourceConnectorConfigTest {

    @Test
    void testWithoutMandatoryParams() {
        assertThrows(ConfigException.class, () -> new RandomSourceConnectorConfig(Collections.emptyMap()));
    }

    @Test
    void testWithMandatoryParams() {
        assertDoesNotThrow(() -> new RandomSourceConnectorConfig(Collections.singletonMap("topic", "test")));
    }
}