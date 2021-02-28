package org.app.connector;

import org.apache.kafka.common.config.ConfigException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RestSinkConnectorConfigTest {
    @Test
    void testWithoutMandatoryParams() {
        assertThrows(ConfigException.class, () -> new RestSinkConnectorConfig(Collections.emptyMap()));
    }

    @Test
    void testWithMandatoryParams() {
        assertDoesNotThrow(() -> new RestSinkConnectorConfig(Collections.singletonMap("api.uri", "test")));
    }
}