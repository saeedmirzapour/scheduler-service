package io.handyprojects.schedulerservice.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.util.StringUtil;
import org.hibernate.Hibernate;

import java.io.IOException;

public class PlanSerializer extends JsonSerializer<Plan> {

    @Override
    public void serialize(Plan value, JsonGenerator gen,
                          SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", StringUtil.valueOf(value.getId()));
        gen.writeStringField("name", StringUtil.valueOf(value.getName()));
        gen.writeStringField("cronString", StringUtil.valueOf(value.getCronString()));
        gen.writeStringField("active", StringUtil.valueOf(value.isActive()));
        if (Hibernate.isInitialized(value.getJobs())) {
            gen.writeObjectField("jobs", value.getJobs());
        }
        gen.writeEndObject();

    }
}
