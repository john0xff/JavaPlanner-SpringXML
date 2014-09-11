package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxText;
import com.dhtmlx.planner.controls.DHXMapView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;

@Controller
public class MapController {

	@RequestMapping("/06_map.html")
    public ModelAndView scheduler_06(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.xy.setMapDateWidth(160);
    	s.xy.setMapDescriptionWidth(300);
    	s.load("06_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("06_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	s.views.add(new DHXMapView());
    	DHXMapView map = (DHXMapView) s.views.getView(3);
    	map.setStartDate(2013, 1, 1);
    	s.setInitialView("map");

    	// adds section in lightbox
    	DHXLightboxText loc = new DHXLightboxText("event_location", "Location");
    	loc.setHeight(40);
    	s.lightbox.add(loc);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Map view");
    	mnv.addObject("sample_name", "Map view");
    	mnv.addObject("sample_dsc", "Map view integrates Agenda view with Google Maps that allows you to display locations associated with the calendar events on the map. You can specify the event location in 2 ways: to specify the address in the event description or to set the point right on Google Maps.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/06_events")
    @ResponseBody public String events_06(HttpServletRequest request) {
    	CustomMapEventsManager evs = new CustomMapEventsManager(request);
    	return evs.run();
    }

}
