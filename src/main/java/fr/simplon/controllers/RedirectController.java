package fr.simplon.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class RedirectController {
	/*
	 * Pont vers une action tierce
	 */
	@RequestMapping (value="/a10", method=RequestMethod.GET)
	public String a10(){
		return "/a01";
	}
	/*
	 * Redirection temporaire 302 vers une action
	 */
	@RequestMapping(value="/a11", method=RequestMethod.GET)
	public String a11(){
		return "redirect:a02";
	}
	@RequestMapping (value="/a12", method=RequestMethod.GET)
	public void a12(HttpServletResponse response){
		response.setStatus(301);
		response.addHeader("location","/a03");
	}

}
