package com.dhtmlx.demoapp.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXFilter;
import com.dhtmlx.planner.controls.DHXRule;
import com.dhtmlx.planner.controls.DHXRule.DHXOperator;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class FilteringController {

	@RequestMapping("/14_filtering.html")
    public ModelAndView scheduler_14(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("14_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("14_events");

    	DHXFilter filter = new DHXFilter("month");
    	filter.addRule(new DHXRule("start_date", DHXOperator.Greater, Calendar.getInstance()));
    	s.views.getView(0).setFilter(filter);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Filtering");
    	mnv.addObject("sample_name", "Events filtering");
    	mnv.addObject("sample_dsc", "You may filter events by one or several criteria, using different filtering rules for different views. Filtering can be applied initially or invoked on some action.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/14_events")
    @ResponseBody public String events_14(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
