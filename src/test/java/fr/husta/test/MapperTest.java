package fr.husta.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import fr.husta.test.pojo.SimpleNumbersPojo;
import fr.husta.test.pojo.SimplePojo;

public class MapperTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();

        // register modules
        mapper.registerModule(new JodaModule());

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    @Test
    public void testMapping_marshal() throws Exception {
        String jsonString;
        SimplePojo testPojo = new SimplePojo();
        testPojo.setName("test-1");
        testPojo.setOldDate(new Date());
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

    @Test
    public void testMappingNumbers() throws Exception {
        String jsonString;
        SimpleNumbersPojo testPojo = new SimpleNumbersPojo();
        testPojo.setNbInt(12);
        testPojo.setNbLong(123123456789L);
        testPojo.setNbFloat(9.896F);
        testPojo.setNbDouble(89.896456789);
        testPojo.setNbBigInteger(new BigInteger("123456789456456456456789"));
        testPojo.setNbBigDecimal(new BigDecimal("123456789456456456456789.5689"));

        jsonString = mapper.writeValueAsString(testPojo);
        System.out.println(jsonString);
    }

}
