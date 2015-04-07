package org.motechproject.helloworld.repository;

import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.helloworld.domain.ActiveMqKaha;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by root on 18/3/15.
 */
public interface ActiveMqKahaDataService extends MotechDataService<ActiveMqKaha> {
    @Lookup
    ActiveMqKaha findRecordByName(@LookupField(name = "name") String recordName);
}