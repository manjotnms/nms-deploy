package org.motechproject.helloworld.service;


import org.motechproject.helloworld.domain.ActiveMqJDBC;

/**
 * Created by root on 19/3/15.
 */
public interface ActiveMqJDBCService {

    void create(String name);

    void add( ActiveMqJDBC record);
}
