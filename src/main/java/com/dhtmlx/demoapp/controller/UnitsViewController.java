package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.controls.DHXUnitsView;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class UnitsViewController {

	@RequestMapping("/08_units_view.html")
    public ModelAndView scheduler_08(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("08_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("08_events");
    	s.setInitialView("topic");

    	// creates units view
    	DHXUnitsView view = new DHXUnitsView("topic", "event_topic", "Topic");
    	view.setServerListLink("topic");
    	s.views.add(view);

    	// adds section in lightbox
    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Units View");
    	mnv.addObject("sample_name", "Units view");
    	mnv.addObject("sample_dsc", "Units View lets you display several calendars for multiple resources simultaneously (e.g. human resources, rooms, etc.). The calendars are arranged in columns, so you can see all of them on one page.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/08_events")
    @ResponseBody public String events_08(HttpServletRequest request) {
    	CustomUnitsEventsManager evs = new CustomUnitsEventsManager(request);
    	return evs.run();
    }

}
