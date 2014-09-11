package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;

@Controller
public class RecurringController {

	@RequestMapping("/04_recurring.html")
    public ModelAndView scheduler_04(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.config.setFullDay(true);
    	s.config.setMultiDay(true);
    	s.setWidth(900);
    	s.load("04_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("04_events");
    	s.extensions.add(DHXExtension.RECURRING);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Recurring events");
    	mnv.addObject("sample_name", "Recurring events");
    	mnv.addObject("sample_dsc", "Recurring events are events that repeat in series, for example, the birthday of your friend, a monthly salary or a weekly staff meeting. Create a recurring event and try to edit it: you may change all occurrences in the series at once or edit just a single instance.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/04_events")
    @ResponseBody public String events_04(HttpServletRequest request) {
    	CustomRecEventsManager evs = new CustomRecEventsManager(request);
    	return evs.run();
    }

}
