package fr.husta.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.husta.test.pojo.SimplePojo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    private ObjectMapper mapper;

    @Before public void setUp() throws Exception {
        mapper = new ObjectMapper();

        // register modules
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    @Test public void testMapping_marshal() throws Exception {
        String jsonString;
        SimplePojo testPojo = new SimplePojo();
        testPojo.setName("test-1");
        testPojo.setDob(LocalDate.of(1975, Month.AUGUST, 31));
        testPojo.setTimestamp(LocalDateTime.now());
        testPojo.setTimestampWithTimezone(ZonedDateTime.now());

        jsonString = mapper.writeValueAsString(testPojo);
        System.out.println(jsonString);

        JsonNode rootNode = mapper.readTree(jsonString);
        JsonNode dobNode = rootNode.get("dob");
        String textValue = dobNode.textValue();
        assertThat(textValue).isNotNull();
    }

}
