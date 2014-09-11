package com.dhtmlx.demoapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class ExportController {

	@RequestMapping("/16_export.html")
    public ModelAndView scheduler_16(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.setWidth(900);
    	s.load("16_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("16_events");
    	s.toPDF();
    	s.toICal("16_ical");

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Export");
    	mnv.addObject("sample_name", "Export to PDF and iCal");
    	mnv.addObject("sample_dsc", "To provide printing capabilities, scheduler supports export to a PDF document. Also, there is support for data export to the iCal format that allows you to share the JavaPlanner data across different apps and devices.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/16_events")
    @ResponseBody public String events_16(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

    @RequestMapping("/16_ical")
    @ResponseBody public String ical_16(HttpServletRequest request, HttpServletResponse response) {
    	String filename = "dhtmlxscheduler.ics";
    	String ical = request.getParameter("ical");
    	if (ical==null)
    		return "";
    	response.reset();
    	response.setContentType("calendar/ical");
    	response.setHeader("Content-disposition", "attachment; filename=\"" +filename +"\"");
    	try {
			response.getOutputStream().print(ical);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return "";
    }

}
