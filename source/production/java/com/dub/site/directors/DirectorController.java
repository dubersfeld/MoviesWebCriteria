package com.dub.site.directors;

import com.dub.directors.DirectorServices;
import com.dub.config.annotation.WebController;
import com.dub.entities.Director;
import com.dub.exceptions.DirectorNotFoundException;
import com.dub.exceptions.DuplicateDirectorException;
import com.dub.site.NameForm;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebController
public class DirectorController {
	
	
	@Resource
	private DirectorServices directorServices;
	
	
	@InitBinder({"director", "directorForm"})	  
	protected void initCreateBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));	    
	}
	
	
	
	@RequestMapping(value = "directorQueries", method = RequestMethod.GET)
	public String directorQueries() {		
		return "directors/directorQueries";
	}
	
	@RequestMapping(value = "listAllDirectors", method = RequestMethod.GET)
	public String listAllDirectors(Map<String, Object> model) {
			
		List<Director> list = directorServices.getAllDirectors();		
		model.put("directors", list);
		return "directors/listDirectors"; 
	}
	
	
	@RequestMapping(value = "numberOfDirectors", method = RequestMethod.GET)
	public String numberOfDirectors(Map<String, Object> model) {		
		long number = directorServices.numberOfDirectors();
		model.put("number", number);
		return "directors/numberOfDirectors";
	}
	
	
	@RequestMapping(value = "getDirector", method = RequestMethod.GET)
	public ModelAndView getDirector(ModelMap model) {
		model.addAttribute("getDirector", new DirectorIdForm());
		return new ModelAndView("directors/getDirector", model);
	}
	
	@RequestMapping(value = "getDirector", method = RequestMethod.POST)
	public String getDirector(
				@Valid @ModelAttribute("getDirector") DirectorIdForm form,
				BindingResult result, ModelMap model) 
	{		
		if (result.hasErrors()) {
			return "directors/getDirector";
		} else {
			try {					
				Director director = 
						directorServices.getDirector(form.getId());	
				model.addAttribute("director", director);
				return "directors/getDirectorResult";
			} catch (DirectorNotFoundException e) {
				model.addAttribute("backPage", "getDirector");
				return "directors/getDirectorNoResult";
			} 
		}
	}
	

	@RequestMapping(value = "getDirectorByName", method = RequestMethod.GET)
	public ModelAndView getDirectorByName(ModelMap model) {
		model.addAttribute("directorName", new NameForm());
		return new ModelAndView("directors/getDirectorByName", model);
	}
	
	@RequestMapping(value = "getDirectorByName", method = RequestMethod.POST)
	public String getDirectorByName(
				@Valid @ModelAttribute("directorName") NameForm form,
				BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {			
			return "directors/getDirectorByName";
		} else {
			try {
				Director director = directorServices.getDirector(
												form.getFirstName(), 
												form.getLastName());
				model.addAttribute("director", director);
				return "directors/getDirectorResult";
			} catch (DirectorNotFoundException e) {
				model.addAttribute("backPage", "getDirectorByName");
				return "directors/getDirectorNoResult";
			}
		}
	} 
	

	@RequestMapping(value = "addDirector", method = RequestMethod.GET)
	public ModelAndView addDirector(ModelMap model) {
		model.addAttribute("directorForm", new DirectorForm());
		return new ModelAndView("directors/addDirector", model);
	}
	
	@RequestMapping(value = "addDirector", method = RequestMethod.POST)
	public String addDirector(
			@Valid @ModelAttribute("directorForm") DirectorForm form,
			BindingResult result, ModelMap model) 
	{	
		if (result.hasErrors()) {
			return "directors/addDirector";
		} else {
			Director director = new Director();
			director.setFirstName(form.getFirstName());
			director.setLastName(form.getLastName());
			director.setBirthDate(form.getBirthDate());
			try {
				directorServices.addDirector(director);			
				model.addAttribute("director", director);
				return "directors/createDirectorSuccess";
			} catch (DuplicateDirectorException e) {
				result.rejectValue("firstName", "duplicate", "name already present");
				return "directors/addDirector";			
			} 
		}		
	}
	
	
	@RequestMapping(value = "deleteDirector", method = RequestMethod.GET)
	public ModelAndView deleteDirector(ModelMap model) {
		model.addAttribute("getDirector", new DirectorIdForm());
		return new ModelAndView("directors/deleteDirector", model);
	}
	
	@RequestMapping(value = "deleteDirector", method = RequestMethod.POST)
	public String deleteDirector(
			@Valid @ModelAttribute("getDirector") DirectorIdForm form,
			BindingResult result, ModelMap model) {	
		if (result.hasErrors()) {
			return "directors/deleteDirector";
		} else {
			try {
				long directorId = form.getId();
				directorServices.deleteDirector(directorId);		
				return "directors/deleteDirectorSuccess";
			} catch (DirectorNotFoundException e) {
				result.rejectValue("id", "notFound", "director not found");				
				return "directors/deleteDirector";
			}
		}
	}
	
	@RequestMapping(value = "updateDirector", method = RequestMethod.GET)
	public ModelAndView updateDirector(ModelMap model) {
		model.addAttribute("getDirector", new DirectorIdForm());
		return new ModelAndView("directors/updateDirector1", model);
	}
			
	@RequestMapping(value = "updateDirector1", method = RequestMethod.POST)
	public String updateDirector(
					@Valid @ModelAttribute("getDirector") DirectorIdForm form,
					BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "directors/updateDirector1";
		} else {
			try {
				Director director = directorServices.getDirector(form.getId());
				model.addAttribute("director", director);
				return "directors/updateDirector2";
			} catch (DirectorNotFoundException e) {
				result.rejectValue("id", "notFound", "director not found");
				return "directors/updateDirector1";				
			} 
		}				
	}
	
	@RequestMapping(value = "updateDirector2", method = RequestMethod.POST)
	public String updateDirector2(
						@Valid @ModelAttribute("director") DirectorUpdateForm form,
						BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "directors/updateDirector2";			
		} else {
			Director director = new Director();
			director.setFirstName(form.getFirstName());
			director.setLastName(form.getLastName());
			director.setBirthDate(form.getBirthDate());
			director.setId(form.getId());
			directorServices.updateDirector(director);			
			return "directors/updateDirectorSuccess";
		}		
	}	
}
