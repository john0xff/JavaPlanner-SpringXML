package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXExternalLightboxForm;
import com.dhtmlx.planner.data.DHXDataFormat;

@Controller
public class CustomLightboxController {

	@RequestMapping("/11_custom_lightbox.html")
    public ModelAndView scheduler_11(HttpServletRequest request) throws Exception {
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	s.setWidth(900);
    	s.setInitialDate(2013, 1, 7);
    	s.config.setScrollHour(8);
    	s.config.setDetailsOnCreate(true);
    	s.config.setDblClickCreate(true);
    	s.config.setDetailsOnDblClick(true);

    	s.load("11_events", DHXDataFormat.JSON);
    	s.data.dataprocessor.setURL("11_events");

    	DHXExternalLightboxForm box = s.lightbox.setExternalLightboxForm("./11_custom_editor.html", 296, 140);    
    	box.setClassName("custom_lightbox");

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Scheduler - Custom lightbox");
    	mnv.addObject("sample_name", "Custom lightbox");
    	mnv.addObject("sample_dsc", "There is the possibility to create a fully custom lightbox. Double click on any empty cell or event in the JavaPlanner and open lightbox that is fully custom and contains regular HTML inputs.");
		mnv.addObject("body", s.render());

        return mnv;
    }

    @RequestMapping("/11_custom_editor.html")
    public ModelAndView scheduler_11_editor(HttpServletRequest request) throws Exception {
        return new ModelAndView("custom_editor");
    }

    @RequestMapping("/11_events")
    @ResponseBody public String events_11(HttpServletRequest request) {
    	CustomEventsManager evs = new CustomEventsManager(request);
    	return evs.run();
    }

}
