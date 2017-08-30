package fr.husta.test.jackson.daatbind.ser.std;

import com.fasterxml.jackson.databind.ser.std.RawSerializer;

public class RawStringSerializer extends RawSerializer<String> {

    public RawStringSerializer() {
        super(String.class);
    }

}
