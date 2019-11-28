package io.handyprojects.schedulerservice.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.util.StringUtil;
import org.hibernate.Hibernate;

import java.io.IOException;

public class JobSerializer extends JsonSerializer<Job> {

    @Override
    public void serialize(Job value, JsonGenerator gen,
                          SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", StringUtil.valueOf(value.getId()));
        gen.writeStringField("curlCommand", StringUtil.valueOf(value.getCurlCommand()));
        gen.writeStringField("order", StringUtil.valueOf(value.getOrder()));
        gen.writeStringField("active", StringUtil.valueOf(value.isActive()));

        if (Hibernate.isInitialized(value.getPlan())) {
            gen.writeFieldName("plan");
            gen.writeStartObject();
            gen.writeObjectField("id", value.getPlan().getId());
            gen.writeObjectField("name", value.getPlan().getName());
            gen.writeObjectField("cronString", value.getPlan().getCronString());
            gen.writeObjectField("active", value.getPlan().isActive());
            gen.writeEndObject();
        }
        gen.writeEndObject();
    }
}
