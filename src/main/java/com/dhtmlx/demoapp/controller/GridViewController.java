package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXGridView;
import com.dhtmlx.planner.controls.DHXGridViewColumn;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;

@Controller
public class GridViewController {

	@RequestMapping("/07_grid_view.html")
    public ModelAndView scheduler_07(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.setWidth(900);
    	s.load("07_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("07_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	DHXGridView view = new DHXGridView();
    	view.addOption(new DHXGridViewColumn("text", "Text"));
    	view.addOption(new DHXGridViewColumn("start_date", "Start date", 150));
    	view.addOption(new DHXGridViewColumn("end_date", "End date", 150));
    	view.setFrom(2013, 1, 1);
    	s.views.add(view);
    	s.setInitialView("grid");

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Grid View");
    	mnv.addObject("sample_name", "Grid view");
    	mnv.addObject("sample_dsc", "Grid view presents a list of upcoming events and unlike the Agenda view, it allows you to configure the number of columns and choose what data to display. It also supports sorting (including custom) and data templates.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/07_events")
    @ResponseBody public String events_07(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
