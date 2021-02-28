package org.app.connector;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OkHttpRequestExecutorConfigTest {

    @Test
    void testWithoutMandatoryParams() {
        assertDoesNotThrow(() -> new OkHttpRequestExecutorConfig(Collections.emptyMap()));
    }

}