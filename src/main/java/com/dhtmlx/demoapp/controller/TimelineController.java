package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.controls.DHXTimelineView.RenderModes;
import com.dhtmlx.planner.controls.DHXTimelineView.XScaleUnits;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class TimelineController {

	@RequestMapping("/09_timeline_view.html")
    public ModelAndView scheduler_09(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.setWidth(900);
    	s.setInitialView("topic");
    	s.load("09_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("09_events");

    	DHXTimelineView view = new DHXTimelineView("topic", "event_topic", "Topic");
    	view.setRenderMode(RenderModes.BAR);
    	view.setXScaleUnit(XScaleUnits.MINUTE);
    	view.setXStep(30);
    	view.setXSize(10);
    	view.setXStart(16);
    	view.setXLength(48);
    	view.setServerList("topic");
    	s.views.add(view);

    	DHXLightboxSelect sel = new DHXLightboxSelect("event_topic", "Topic");
    	sel.setServerList("topic");
    	s.lightbox.add(sel);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Timeline view");
    	mnv.addObject("sample_name", "Timeline view");
    	mnv.addObject("sample_dsc", "Timeline View can be used to visualize and monitor the progress of ongoing tasks or projects. The vertical scale in the view presents event holders, while the horizontal scale is a configurable time-scale. There is the possibility to enable the hierarchical structure and show the resources in expandable groups.");
		mnv.addObject("body", s.render());

        return mnv;
    }

	@RequestMapping("/09_events")
    @ResponseBody public String events_09(HttpServletRequest request) {
    	CustomUnitsEventsManager evs = new CustomUnitsEventsManager(request);
    	return evs.run();
    }
}
