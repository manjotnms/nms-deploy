package org.motechproject.helloworld.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * Created by root on 19/3/15.
 */
@Entity
public class ActiveMqJDBC {
    @Field(required = true)

    private String name;

    public ActiveMqJDBC() {
    }


    public ActiveMqJDBC(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
