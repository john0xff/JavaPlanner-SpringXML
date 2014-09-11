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
public class LoadingController {

	@RequestMapping("/02_loading.html")
    public ModelAndView scheduler_02(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("02_events", DHXDataFormat.JSON);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Events loading");
    	mnv.addObject("sample_name", "Loading");
    	mnv.addObject("sample_dsc", "JavaPlanner can load data of ICal, JSON, XML formats from a file or object. There is support for static and dynamic loading modes.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/02_events")
    @ResponseBody public String events_02(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
