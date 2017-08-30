package fr.husta.test.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.husta.test.jackson.daatbind.ser.std.RawStringSerializer;

public class JsonRawValuePojo {

    private int id;

    private String name;

    private String nameJsonString;

    private List<String> listRawJson;

    public JsonRawValuePojo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonRawValue
    public String getNameJsonString() {
        return nameJsonString;
    }

    public void setNameJsonString(String nameJsonString) {
        this.nameJsonString = nameJsonString;
    }

    // @JsonRawValue
    @JsonSerialize(contentUsing = RawStringSerializer.class)
    public List<String> getListRawJson() {
        return listRawJson;
    }

    public void setListRawJson(List<String> listRawJson) {
        this.listRawJson = listRawJson;
    }
}
