package org.motechproject.helloworld.event.handler;

import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.motechproject.helloworld.domain.ActiveMqJDBC;
import org.motechproject.helloworld.domain.ActiveMqKaha;
import org.motechproject.helloworld.domain.HelloWorldRecord;
import org.motechproject.helloworld.event.HelloWorldEventParams;
import org.motechproject.helloworld.event.HelloWorldEventSubjects;
import org.motechproject.helloworld.service.ActiveMqJDBCService;
import org.motechproject.helloworld.service.ActiveMqKahaService;
import org.motechproject.helloworld.service.HelloWorldRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HelloWorldEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ActiveMqKahaService activeMqKahaService;

    private ActiveMqJDBCService activeMqJDBCService;

    @Autowired
    public HelloWorldEventHandler(ActiveMqKahaService activeMqKahaService, ActiveMqJDBCService activeMqJDBCService) {
        this.activeMqKahaService = activeMqKahaService;
        this.activeMqJDBCService = activeMqJDBCService;
    }

    @MotechListener(subjects = {HelloWorldEventSubjects.SEND_HELLO})
        public void handleCreateHelloEvents(MotechEvent event) {
            Long timenow = System.currentTimeMillis();
            logger.debug("Event Received");
            String timethan = (String)event.getParameters().get(HelloWorldEventParams.TIME);
            logger.debug("Time than was:" + timethan);
            logger.debug("Time Now is:" + timenow);
            String Diff = Long.toString(timenow - Long.parseLong(timethan));
            logger.debug("Time Diff is:" +Diff);
            ActiveMqKaha record = new ActiveMqKaha(Diff);
        activeMqKahaService.add(record);


        }


    @MotechListener(subjects = {HelloWorldEventSubjects.CREATE_HELLO_RECORD})
    public void handleJDBCEvent(MotechEvent event) {
        Long timenow = System.currentTimeMillis();
        logger.debug("Event Received in handleJDBCEvent");
        String timethan = (String)event.getParameters().get(HelloWorldEventParams.TIME);
        logger.debug("Time than was:" + timethan);
        logger.debug("Time Now is:" + timenow);
        String Diff = Long.toString(timenow - Long.parseLong(timethan));
        logger.debug("Time Diff is:" +Diff);
        ActiveMqJDBC record = new ActiveMqJDBC(Diff);
        activeMqJDBCService.add(record);


    }
}
