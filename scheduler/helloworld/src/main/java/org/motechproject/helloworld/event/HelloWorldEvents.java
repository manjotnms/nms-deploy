package org.motechproject.helloworld.event;

import org.joda.time.DateTime;
import org.motechproject.event.MotechEvent;

import java.util.HashMap;
import java.util.Map;

public final class HelloWorldEvents {
    public static MotechEvent sendHelloWorldEvent() {
        Map<String, Object> params = new HashMap<>();
        String timeq = Long.toString(System.currentTimeMillis());
        params.put(HelloWorldEventParams.TIME,timeq);
        return new MotechEvent(HelloWorldEventSubjects.SEND_HELLO,params);
    }

    public static MotechEvent JDBCEvent() {
        Map<String, Object> params = new HashMap<>();
        String timeq = Long.toString(System.currentTimeMillis());
        params.put(HelloWorldEventParams.TIME,timeq);
        return new MotechEvent(HelloWorldEventSubjects.CREATE_HELLO_RECORD,params);
    }

    private HelloWorldEvents() {}
}
