package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class SecurityController {

	@RequestMapping("/15_security.html")
    public ModelAndView scheduler_15(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("15_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("15_events");

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Security");
    	mnv.addObject("sample_name", "Security");
    	mnv.addObject("sample_dsc", "By default, any user can edit data in the JavaPlanner and the related changes will be saved in the database. Built-in security manager will let you limit access to 'read', 'insert', 'update' and 'delete' operations for specific groups of users.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/15_events")
    @ResponseBody public String events_15(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	evs.security.deny(DHXStatus.UPDATE);
    	return evs.run();
    }
	
}
