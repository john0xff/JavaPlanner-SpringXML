package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXAgendaView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;

@Controller
public class AgendaController {

	@RequestMapping("/05_agenda.html")
    public ModelAndView scheduler_05(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setWidth(900);
    	s.setInitialDate(2013, 1, 7);
    	s.load("05_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("05_events");
    	s.extensions.add(DHXExtension.RECURRING);
    	s.views.add(new DHXAgendaView());
    	s.setInitialView("agenda");
    	DHXAgendaView agenda = (DHXAgendaView) s.views.getView(3);
    	agenda.setStartDate(2012, 11, 20);
    	agenda.setEndDate(2013, 02, 01);

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Agenda view");
    	mnv.addObject("sample_name", "Agenda view");
    	mnv.addObject("sample_dsc", "Agenda view presents a list of ongoing and upcoming events in the order that they are to be taken place. The view contains 2 columns: one for the event date and the other for description.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/05_events")
    @ResponseBody public String events_05(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
