package org.motechproject.helloworld.service;

/**
 * Created by root on 18/3/15.
 */

import org.motechproject.helloworld.domain.ActiveMqKaha;

public interface ActiveMqKahaService {

    void create(String name);

    void add( ActiveMqKaha record);

}
