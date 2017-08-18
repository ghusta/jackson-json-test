package fr.husta.test.pojo;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class SimplePojo {

    private String name;
    private int age;
    private LocalDate dob;
    private LocalDateTime timestamp;
    private DateTime timestampWithTimezone;

    public SimplePojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public DateTime getTimestampWithTimezone() {
        return timestampWithTimezone;
    }

    public void setTimestampWithTimezone(DateTime timestampWithTimezone) {
        this.timestampWithTimezone = timestampWithTimezone;
    }
}
