package fr.husta.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.husta.test.pojo.JsonRawValuePojo;
import fr.husta.test.pojo.SimplePojo;

public class MapperTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();

        // register modules
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    @Test
    public void testMapping_marshal() throws Exception {
        String jsonString;
        SimplePojo testPojo = new SimplePojo();
        testPojo.setName("test-1");
        testPojo.setDob(LocalDate.of(1975, Month.AUGUST, 31));
        testPojo.setTimestamp(LocalDateTime.now());
        testPojo.setTimestampWithTimezone(ZonedDateTime.now());
        testPojo.setTimestampWithTimezoneOffset(OffsetDateTime.now());
        testPojo.setInstant(Instant.now());

        jsonString = mapper.writeValueAsString(testPojo);
        System.out.println(jsonString);

        JsonNode rootNode = mapper.readTree(jsonString);
        JsonNode dobNode = rootNode.get("dob");
        String textValue = dobNode.textValue();
        assertThat(textValue).isNotNull();
    }

    @Test
    public void testJsonRawValue() throws Exception {
        JsonRawValuePojo rawValuePojo = new JsonRawValuePojo();
        rawValuePojo.setId(1);
        rawValuePojo.setName("Hello good bye");
        rawValuePojo.setNameJsonString("\"Hello Hello Hello\"");
        List<String> listRaw = new ArrayList<>();
        listRaw.add("\"Coucou\"");
        listRaw.add(" \"Au revoir\"  ");
        listRaw.add("{\"city_id\":2,\"city\":\"Abha\",\"country_id\":82,\"last_update\":\"2006-02-15T09:45:25\"}");
        listRaw.add("{\"city_id\":58,\"city\":\"Batman\",\"country_id\":97,\"last_update\":\"2006-02-15T09:45:25\"}");
        rawValuePojo.setListRawJson(listRaw);

        String jsonString = mapper.writeValueAsString(rawValuePojo);
        System.out.println(jsonString);

        JsonNode rootNode = mapper.readTree(jsonString);
        assertThat(rootNode).isNotNull();
        JsonNode rawJsonNode = rootNode.get("listRawJson");
        assertThat(rawJsonNode).isNotNull();
        assertThat(rawJsonNode.isArray()).isTrue();
    }

}
