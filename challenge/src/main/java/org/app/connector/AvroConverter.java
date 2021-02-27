package org.app.connector;

import io.confluent.connect.avro.AvroData;
import io.confluent.connect.avro.AvroDataConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.connect.data.SchemaAndValue;

import java.util.HashMap;

public class AvroConverter<R extends SpecificRecord> {

    public SchemaAndValue getSchemaAndValue(R record) {
        AvroData avroData = new AvroData(new AvroDataConfig(new HashMap<>()));
        return avroData.toConnectData(record.getSchema(), record);
    }
}
