package org.app.connector;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.kafka.common.Configurable;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
public class OkHttpRequestExecutor implements Configurable {

    private OkHttpClient client;

    /**
     * Method that configures OkHttpClient
     *
     * @param props - properties for configuration
     * @see OkHttpClient
     */
    public void configure(Map<String, ?> props) {
        final OkHttpRequestExecutorConfig config = new OkHttpRequestExecutorConfig(props);

        client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(config.getMaxIdleConnections(), config.getKeepAliveDuration(), TimeUnit.MILLISECONDS))
                .connectTimeout(config.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * Method that executes request to external api
     *
     * @param request - request that needs to be executed( in our case post request with record body )
     * @return - indicator for understanding status of executed request
     */
    @SneakyThrows
    public boolean execute(Request request) {
        log.info("Making request to: " + request);

        return client.newCall(request).execute().isSuccessful();
    }
}
