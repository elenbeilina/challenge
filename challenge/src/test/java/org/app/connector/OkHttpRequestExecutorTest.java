package org.app.connector;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OkHttpRequestExecutorTest {

    @Test
    void configure() {
        OkHttpRequestExecutor executor = new OkHttpRequestExecutor();
        executor.configure(Collections.emptyMap());
        OkHttpClient client = executor.getClient();

        assert client.connectTimeoutMillis() ==  2000;
    }
}