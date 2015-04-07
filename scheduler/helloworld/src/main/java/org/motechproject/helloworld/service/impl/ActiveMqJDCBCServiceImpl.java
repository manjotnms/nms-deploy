package org.motechproject.helloworld.service.impl;

import org.motechproject.helloworld.domain.ActiveMqJDBC;
import org.motechproject.helloworld.repository.ActiveMqJDBCDataService;
import org.motechproject.helloworld.service.ActiveMqJDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 19/3/15.
 */
@Service("activemqjdbcService")
public class ActiveMqJDCBCServiceImpl implements ActiveMqJDBCService {

    private ActiveMqJDBCDataService activeMqJDBCDataService;

    @Autowired
    public ActiveMqJDCBCServiceImpl(ActiveMqJDBCDataService activeMqJDBCDataService) {
        this.activeMqJDBCDataService = activeMqJDBCDataService;
    }

    @Override
    public void create(String name ) {
        activeMqJDBCDataService.create(
                new ActiveMqJDBC(name));
    }

    @Override
    public void add(ActiveMqJDBC record) {
        activeMqJDBCDataService.create(record);
    }
}

