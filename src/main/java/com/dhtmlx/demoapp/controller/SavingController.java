package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class SavingController {

	@RequestMapping("/03_saving.html")
    public ModelAndView scheduler_03(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("03_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("03_events");

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Saving events");
    	mnv.addObject("sample_name", "Saving");
    	mnv.addObject("sample_dsc", "JavaPlanner provides simple integration with the server side. Add, edit, delete events and then, reload the page. All the changes will be saved.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/03_events")
    @ResponseBody public String events_03(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
