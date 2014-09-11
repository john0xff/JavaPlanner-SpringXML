package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class LightboxController {

	@RequestMapping("/10_lightbox.html")
    public ModelAndView scheduler_10(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("10_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("10_events");
    	s.config.setDetailsOnCreate(true);
    	s.config.setDetailsOnDblClick(true);

    	// lightbox configuring
    	s.lightbox.get("description").setHeight(30);
    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel,1);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Lightbox");
    	mnv.addObject("sample_name", "Lightbox");
    	mnv.addObject("sample_dsc", "Lightbox is an editor of JavaPlanner's events. To open lightbox, double click on any cell in the JavaPlanner. Lighbox can contain any number of controls (by default, a text field for the event description and selects for setting the event duration).");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/10_events")
    @ResponseBody public String events_10(HttpServletRequest request) {
    	CustomUnitsEventsManager evs = new CustomUnitsEventsManager(request);
    	return evs.run();
    }

}
