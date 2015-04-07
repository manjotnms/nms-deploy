package org.motechproject.helloworld.repository;


import org.motechproject.helloworld.domain.ActiveMqJDBC;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

/**
 * Created by root on 19/3/15.
 */
public interface ActiveMqJDBCDataService extends MotechDataService<ActiveMqJDBC> {
    @Lookup
    ActiveMqJDBC findRecordByName(@LookupField(name = "name") String recordName);
}
