package org.app.connector;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestSinkTaskTest {

    @Spy
    private final RestSinkConnectorConfig config =
            new RestSinkConnectorConfig(Collections.singletonMap("api.uri", "http://test"));
    @Mock
    private OkHttpRequestExecutor executor;
    @InjectMocks
    private RestSinkTask restSinkTask;

    private List<SinkRecord> sinkRecords;

    @BeforeEach
    void setUp() {
        sinkRecords = Collections.singletonList(
                new SinkRecord("test", 1, Schema.STRING_SCHEMA, "test",
                        Schema.STRING_SCHEMA, "test", 1));
    }

    @Test
    void putSuccess() {
        when(executor.execute(any())).thenReturn(true);
        restSinkTask.put(sinkRecords);

        verify(executor, times(1)).execute(any());
    }

    @Test
    void putNotSucceeded() {
        when(executor.execute(any()))
                .thenReturn(false)
                .thenReturn(true);
        restSinkTask.put(sinkRecords);

        verify(executor, times(2)).execute(any());
    }

    @Test
    void putNotSucceededCheckMaxRetries() {
        when(executor.execute(any()))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);
        restSinkTask.put(sinkRecords);

        verify(executor, times(5)).execute(any());
    }
}