package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxMiniCalendar;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class MiniCalendarsController {

	@RequestMapping("/12_mini_calendars.html")
    public ModelAndView scheduler_12(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("12_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("12_events");

    	s.calendars.attachMiniCalendar();
    	s.lightbox.add(new DHXLightboxMiniCalendar("cal", "Time period"));

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Mini Calendars");
    	mnv.addObject("sample_name", "Mini calendars");
    	mnv.addObject("sample_dsc", "To simplify navigation, you can display a small date picker on the left side of the JavaPlanner header. Also, you can put a date picker in the lightbox or any HTML container outside the JavaPlaner.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/12_events")
    @ResponseBody public String events_12(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
