package org.motechproject.helloworld.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.Objects;

/**
 * Models data for simple records in a portable manner.
 */
@Entity
public class HelloWorldRecord {

    @Field(required = true)

    private String name;

    public HelloWorldRecord() {
    }


    public HelloWorldRecord(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final HelloWorldRecord other = (HelloWorldRecord) obj;

        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return String.format("HelloWorldRecord{name='%s', message='%s'}", name);
    }
}
