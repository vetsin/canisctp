package com.canisctp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController 
{
	@RequestMapping(value = "/")
	public ModelAndView home(final ModelMap model)
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("home", "hiw orld");
		return mv;
	}
}
