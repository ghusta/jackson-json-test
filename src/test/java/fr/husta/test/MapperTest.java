package fr.husta.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import fr.husta.test.pojo.SimplePojo;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    private ObjectMapper mapper;

    @Before public void setUp() throws Exception {
        mapper = new ObjectMapper();

        // register modules
        mapper.registerModule(new JodaModule());

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    @Test public void testMapping_marshal() throws Exception {
        String jsonString;
        SimplePojo testPojo = new SimplePojo();
        testPojo.setName("test-1");
        testPojo.setDob(new LocalDate(1975, 8, 31));
        testPojo.setTimestamp(LocalDateTime.now());
        testPojo.setTimestampWithTimezone(DateTime.now());

        jsonString = mapper.writeValueAsString(testPojo);
        System.out.println(jsonString);

        JsonNode rootNode = mapper.readTree(jsonString);
        JsonNode dobNode = rootNode.get("dob");
        String textValue = dobNode.textValue();
        assertThat(textValue).isNotNull();
    }

}
