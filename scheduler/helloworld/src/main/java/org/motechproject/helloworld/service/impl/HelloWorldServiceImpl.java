package org.motechproject.helloworld.service.impl;

import org.motechproject.event.listener.EventRelay;
import org.motechproject.helloworld.event.HelloWorldEventParams;
import org.motechproject.helloworld.event.HelloWorldEventSubjects;
import org.motechproject.helloworld.event.HelloWorldEvents;
import org.motechproject.helloworld.service.HelloWorldService;
import org.motechproject.event.MotechEvent;
import org.motechproject.scheduler.contract.CronSchedulableJob;
import org.motechproject.scheduler.service.MotechSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    @Autowired
    private EventRelay eventRelay;

    @Autowired
    private MotechSchedulerService schedulerService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String sayHello( String name, String message ) {
        //eventRelay.sendEventMessage( HelloWorldEvents.sendHelloWorldEvent() );
        Map<String, Object> params = new HashMap<>();
        String timeq = Long.toString(System.currentTimeMillis());
        params.put(HelloWorldEventParams.TIME,timeq);
        MotechEvent motechEvent = new MotechEvent(HelloWorldEventSubjects.SEND_HELLO, params);

        logger.debug("Event constructed");

        String cronExpression = "0 0/5 * * * ?";
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        CronSchedulableJob cronJob = new CronSchedulableJob(motechEvent, cronExpression, today, null, true);
        schedulerService.safeScheduleJob(cronJob);
        logger.debug("Job scheduled");
        return "Hello World";
    }

    @Override
    public String JDBCTest () {
        eventRelay.sendEventMessage(HelloWorldEvents.JDBCEvent());
        return "Hello JDBC";
    }


}
