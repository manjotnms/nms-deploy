package org.motechproject.helloworld.web;

import org.motechproject.helloworld.service.HelloWorldRecordService;
import org.motechproject.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for HelloWorld message and bundle status.
 */
@Controller
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private HelloWorldRecordService helloWorldRecordService;

    private static final String OK = "OK";

    @RequestMapping("/web-api/status")
    @ResponseBody
    public String status() {
        return OK;
    }

    @RequestMapping("/kaha")
    @ResponseBody
    public String sayHello( ) {
        String name ="";
        String message= "";
        try {
            helloWorldService.sayHello( name, message );
        } catch(Exception e) {
        }

        return String.format("{\"Kaha\":\"%s\"}", OK );
    }
    @RequestMapping("/jdbc")
    @ResponseBody
    public String JDBCTest (){
        try{
            helloWorldService.JDBCTest();
        }catch(Exception e) {
        }
        return String.format("{\"JDBC\":\"%s\"}", OK  );
    }




}
