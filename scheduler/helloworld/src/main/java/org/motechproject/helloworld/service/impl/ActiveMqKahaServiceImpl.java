package org.motechproject.helloworld.service.impl;

import org.motechproject.helloworld.domain.ActiveMqKaha;
import org.motechproject.helloworld.repository.ActiveMqKahaDataService;
import org.motechproject.helloworld.service.ActiveMqKahaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 18/3/15.
 */
@Service("activemqkahaService")
public class ActiveMqKahaServiceImpl implements ActiveMqKahaService {

    private ActiveMqKahaDataService activeMqKahaDataService;

    @Autowired
    public ActiveMqKahaServiceImpl(ActiveMqKahaDataService activeMqKahaDataService) {
        this.activeMqKahaDataService = activeMqKahaDataService;
    }

    @Override
    public void create(String name ) {
        activeMqKahaDataService.create(
                new ActiveMqKaha(name));
    }

    @Override
    public void add(ActiveMqKaha record) {
        activeMqKahaDataService.create(record);
    }
}
