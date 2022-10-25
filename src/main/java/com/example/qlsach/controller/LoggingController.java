package com.example.qlsach.controller;

import com.example.qlsach.until.LogMarkerUntil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class LoggingController {

    @RequestMapping("/login")
    public String login(){

        LogMarkerUntil.loggerLogin.trace("Trace logging");
        LogMarkerUntil.loggerLogin.debug("Debug logging");
        LogMarkerUntil.loggerLogin.info("Info logging");
        LogMarkerUntil.loggerLogin.warn("Warn logging");
        LogMarkerUntil.loggerLogin.error("Error logging");
        return "Check out the login file";
    }

    @RequestMapping("/comment")
    public String comment(){

        LogMarkerUntil.loggerComment.trace("Trace logging");
        LogMarkerUntil.loggerComment.debug("Debug logging");
        LogMarkerUntil.loggerComment.info("Info logging");
        LogMarkerUntil.loggerComment.warn("Warn logging");
        LogMarkerUntil.loggerComment.error("Error logging");
        return "Check out the comment file";
    }

    @RequestMapping("/test")
    public String test(){

        LogMarkerUntil.loggerTest.trace("Trace logging");
        LogMarkerUntil.loggerTest.debug("Debug logging");
        LogMarkerUntil.loggerTest.info("Info logging");
        LogMarkerUntil.loggerTest.warn("Warn logging");
        LogMarkerUntil.loggerTest.error("Error logging");
        return "Check out the test file";
    }
}
