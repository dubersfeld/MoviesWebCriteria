package com.dub.spring.site.actors;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dub.spring.actors.ActorServices;
import com.dub.spring.entities.Actor;
import com.dub.spring.entities.ActorWithPhoto;
import com.dub.spring.exceptions.ActorNotFoundException;
import com.dub.spring.exceptions.DuplicateActorException;
import com.dub.spring.exceptions.PhotoNotFoundException;
import com.dub.spring.site.NameForm;


@Controller
public class ActorController {

	@Value("${photoTempDir}")
	private String photoTempDir;
	
	@Resource
	private ActorServices actorServices;
	
	@InitBinder({"actor", "actorForm"})	  
	protected void initCreateBinder(WebDataBinder binder) {		   
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@RequestMapping(value = "/actorQueries", method = RequestMethod.GET)
	public String actorQueries() {
		return "actors/actorQueries";
	}
	
	@RequestMapping(value = "/allActors", method = RequestMethod.GET)
	public String listAllActors(Map<String, Object> model) {
		List<Actor> list = actorServices.getAllActors();		
		model.put("actors", list);
		System.out.println("Fucking /allActors begin");
		return "actors/allActors";
	} 
	
	
	@RequestMapping(value = "/numberOfActors", method = RequestMethod.GET)
	public String numberOfActors(Map<String, Object> model) {		
		Long number = actorServices.numberOfActors();
		model.put("number", number);
		return "actors/numberOfActors";
	}
	
	
	@RequestMapping(value = "/getActor", method = RequestMethod.GET)
	public ModelAndView getActor(ModelMap model) {
	
		model.addAttribute("actorIdForm", new ActorIdForm());
		return new ModelAndView("actors/getActor", model);
	}
	
	@RequestMapping(value = "/getActor", method = RequestMethod.POST)
	public String getActor(
				@Valid @ModelAttribute("actorIdForm") ActorIdForm form,
				BindingResult result, ModelMap model) {			

		if (result.hasErrors()) {
			return "actors/getActor";
		} else {
			try {		
				long actorId = form.getId();
				Actor actor = actorServices.getActor(actorId);
				model.addAttribute("actor", actor);
				return "actors/getActorResult";
			} catch (ActorNotFoundException e) {
				model.addAttribute("backPage", "getActor");
				return "actors/getActorNoResult";
			} 
		}
	}
	
	
	@RequestMapping(value = "/getActorByName", method = RequestMethod.GET)
	public ModelAndView getActorByName(ModelMap model) {
		model.addAttribute("actorName", new NameForm());
		return new ModelAndView("actors/getActorByName", model);
	}
	
	@RequestMapping(value = "/getActorByName", method = RequestMethod.POST)
	public String getActorByName(
				@Valid @ModelAttribute("actorName") NameForm form,
				BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "actors/getActorByName";
		} else {
			try {				
				Actor actor = actorServices.getActor(
										form.getFirstName(), 
										form.getLastName());
				model.put("actor", actor);
				return "actors/getActorResult";		
			} catch (ActorNotFoundException e) {
				model.addAttribute("backPage", "getActorByName");
				return "actors/getActorNoResult";
			} 
		}
	} 
	
	
	@RequestMapping(value = "/createActor", method = RequestMethod.GET)
	public ModelAndView addActor(ModelMap model) {
		model.addAttribute("actorForm", new ActorForm());
		return new ModelAndView("actors/createActor", model);
	}
	
	@RequestMapping(value = "/createActor", method = RequestMethod.POST)
	public String addActor(
			@Valid @ModelAttribute("actorForm") ActorForm form, 
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "actors/createActor";
		} else {
			Actor actor = new Actor();
			actor.setFirstName(form.getFirstName());
			actor.setLastName(form.getLastName());
			actor.setBirthDate(form.getBirthDate());

			try {				
				actorServices.addActor(actor);			
				model.addAttribute("actor", actor);
				return "actors/createActorSuccess";
			} catch (DuplicateActorException e) {				
				result.rejectValue("firstName", "duplicate", "name already present");
				return "actors/createActor";	
			} 
		}
	}
	
	
	@RequestMapping(value = "/deleteActor", method = RequestMethod.GET)
	public ModelAndView deleteActor(ModelMap model) {
		model.addAttribute("getActor", new ActorIdForm());
		return new ModelAndView("actors/deleteActor", model);
	}
	
	@RequestMapping(value = "/deleteActor", method = RequestMethod.POST)
	public String deleteActor(
			@Valid @ModelAttribute("getActor") ActorIdForm form,
			BindingResult result, ModelMap model) {	
		if (result.hasErrors()) {
			return "actors/deleteActor";
		} else {
			try {
				long actorId = form.getId();
				actorServices.deleteActor(actorId);		
				return "actors/deleteActorSuccess";
			} catch (ActorNotFoundException e) {
				result.rejectValue("id", "notFound", "actor not found");				
				return "actors/deleteActor";
			} 
		}
	}
	
	@RequestMapping(value = "/updateActor", method = RequestMethod.GET)
	public ModelAndView updateActor(ModelMap model) {
		model.addAttribute("getActor", new ActorIdForm());
		return new ModelAndView("actors/updateActor1", model);
	}
			
	@RequestMapping(value = "/updateActor1", method = RequestMethod.POST)
	public String updateActor(
				@Valid @ModelAttribute("getActor") ActorIdForm form,
				BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "actors/updateActor1";
		} else {
			try {
				long actorId = form.getId();
				Actor actor = actorServices.getActor(actorId);
				model.addAttribute("actor", actor);
				return "actors/updateActor2";
			} catch (ActorNotFoundException e) {
				result.rejectValue("id", "notFound", "actor not found");							
				return "actors/updateActor1";
			} 
		}				
	}
	
	@RequestMapping(value = "/updateActor2", method = RequestMethod.POST)
	public String updateActor2(
						@Valid @ModelAttribute("actor") ActorUpdateForm form,
						BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "actors/updateActor2";			
		} else {
			Actor actor = new Actor();
			actor.setFirstName(form.getFirstName());
			actor.setLastName(form.getLastName());
			actor.setBirthDate(form.getBirthDate());
			actor.setId(form.getId());
			actorServices.updateActor(actor);			
			return "actors/updateActorSuccess";
		}		
	}
	
	
	@RequestMapping(
			value = "/createActorPhotoMulti", 
			method = RequestMethod.GET)
	public ModelAndView createActorPhotoMulti(ModelMap model) {
		model.addAttribute("actorPhotoMulti", new ActorPhotoMultiForm());
		return new ModelAndView("actors/createActorPhotoMultipart", model);
	}

	
	@RequestMapping(
    		value = "/createActorPhotoMulti",
    		method = RequestMethod.POST)      	 
	public String uploadActorPhoto(
            @Valid @ModelAttribute("actorPhotoMulti") ActorPhotoMultiForm form, 
            BindingResult result) {	 	
		if (result.hasErrors()) {
			return "actors/createActorPhotoMultipart";
		}
	
		// Get name of uploaded file.
		MultipartFile uploadedFileRef = null;
		String fileName;
		long actorId= form.getActorId();
	 
		uploadedFileRef = form.getUploadedFile();
		fileName = form.getUploadedFile().getOriginalFilename();
	 
		String path = photoTempDir + fileName; 
       	 
		File outputFile = new File(path);
	 
		InputStream is = null;     
		OutputStream os = null;
	
		// This buffer will store the data read from 'uploadedFileRef'
		byte[] buffer = new byte[1000];
		int bytesRead = -1;
		int totalBytes = 0;
    
		// actual photo upload
		try {
			is = uploadedFileRef.getInputStream();
			os = new FileOutputStream(outputFile);
		
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer);
				totalBytes += bytesRead;
			}
	
			if (totalBytes == 0 || totalBytes != uploadedFileRef.getSize()) {
				return "error";
			}
		} catch (FileNotFoundException e) {		
			result.rejectValue("uploadedFile", "notFound", "file not found");
			return "actors/createActorPhotoMultipart";	
		} catch (Exception e) {
			e.printStackTrace();	
		} finally{             
			try {
				is.close();
				os.close();
			} catch (NullPointerException e) {
				return "actors/createActorPhotoMultipart";	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	    
		/**   	      
		 * Now persist actor photo to the database  
		 */  
		CreateActorPhoto createActorPhoto = new CreateActorPhoto();	        
		createActorPhoto.setActorId(actorId);	        
		createActorPhoto.setImageFile(path);
        
		try {
			if (totalBytes > 65535) {
				// photo size too large for a BLOB		 
				result.rejectValue("uploadedFile", "size", "file exceeds 65535 bytes");
				return "actors/createActorPhotoMultipart";	
			}
			actorServices.createActorPhoto(createActorPhoto);
		} catch (ActorNotFoundException e) {
			result.rejectValue("actorId", "notFound", "actor not found");							
			return "actors/createActorPhotoMultipart";	
		} catch (IOException e) {
			return "error";
		} finally {
			// always delete temporary file 
			try {
				Files.deleteIfExists(outputFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}	  
		}
           
		return "actors/createActorPhotoSuccess"; 
	}
	
	
	@RequestMapping(value = "/doGetActorPhoto/{photoId}", method = RequestMethod.GET)
	public @ResponseBody byte[] doGetActorPhoto(@PathVariable("photoId") long photoId)  {
		try {
			byte[] imageBytes = actorServices.getPhotoData(photoId);
			return imageBytes;
		} catch (PhotoNotFoundException e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/getAllPhotosByActor", method = RequestMethod.GET)
	public ModelAndView getAllPhotosByActor(ModelMap model) {
		model.addAttribute("actorName", new NameForm());
		return new ModelAndView("actors/getAllPhotosByActor", model);
	}
		
	@RequestMapping(value = "/getAllPhotosByActor", method = RequestMethod.POST)
	public String getAllPhotosByActor(
				@Valid @ModelAttribute("actorName") NameForm form,
				BindingResult result, ModelMap model) {			
		if (result.hasErrors()) {
			return "actors/getAllPhotosByActor";
		} else {						
			try {
				Actor actor = actorServices.getActor(
												form.getFirstName(),
												form.getLastName());
				List<Long> photoIds = 
								actorServices.getAllPhotoIds(actor);
				model.addAttribute("photoIds", photoIds);
				model.addAttribute("firstName", actor.getFirstName());
				model.addAttribute("lastName", actor.getLastName());
				return "actors/getAllPhotosByActorResult";
			} catch (ActorNotFoundException e) {				
				model.addAttribute("backPage", "getAllPhotosByActor");
				return "actors/getActorNoResult";
			} 
		}
	}
	
	
	@RequestMapping(value = "/listAllActorsWithPhotos", method = RequestMethod.GET)
	public String listAllActorsWithPhotos(Map<String, Object> model) {	
		List<Actor> list;
		List<ActorWithPhoto> actors = new ArrayList<>();
		list = actorServices.getAllActors();	
		for (Actor actor : list) {
			ActorWithPhoto actw = new ActorWithPhoto(actor);
			try {
				long photoId = actorServices.getPhotoId(actor);
				if (photoId > 0) {
					actw.setPhotoAvailable(true);
					actw.setPhotoId(photoId);
				}
			} catch(PhotoNotFoundException e) {
				actw.setPhotoAvailable(false);
			}																
			actors.add(actw);
		}	
		model.put("actors", actors);
		return "actors/listActorsWithPhoto";
	}
	
	
	@RequestMapping(value = "/getActorWithPhotoByName", method = RequestMethod.GET)
	public ModelAndView getActorWithPhotoByName(ModelMap model) {
		model.addAttribute("actorName", new NameForm());
		return new ModelAndView("actors/getActorWithPhotoByName", model);
	}
	
	@RequestMapping(value = "/getActorWithPhotoByName", method = RequestMethod.POST)
	public String getActorWithPhotoByName(
				@Valid @ModelAttribute("actorName") NameForm form,
				BindingResult result, ModelMap model) {			
		if (result.hasErrors()) {
			return "actors/getActorWithPhotoByName";
		} else {
			try {								
				Actor actor = actorServices.getActor(
												form.getFirstName(), 
												form.getLastName());
				ActorWithPhoto actw = new ActorWithPhoto(actor);
				try {
					long photoId = actorServices.getPhotoId(actor);
					actw.setPhotoId(photoId);
					actw.setPhotoAvailable(true);
				} catch (PhotoNotFoundException e) {
					actw.setPhotoAvailable(false);
				} 											
				model.addAttribute("actor", actw);	
				return "actors/getActorWithPhotoResult";
			} catch (ActorNotFoundException e) {
				model.addAttribute("backPage", "getActorWithPhotoByName");
				return "actors/getActorNoResult";
			} 
		}
	}
	
	@RequestMapping(value = "/deleteActorPhoto", method = RequestMethod.GET)
	public ModelAndView deleteActorPhoto(ModelMap model) {
		model.addAttribute("getActorPhoto", new PhotoIdForm());
		return new ModelAndView("actors/deleteActorPhoto", model);
	}
	
	@RequestMapping(value = "/deleteActorPhoto", method = RequestMethod.POST)
	public String deleteActorPhoto(
			@Valid @ModelAttribute("getActorPhoto") PhotoIdForm form,
			BindingResult result, ModelMap model) {			
		if (result.hasErrors()) {			
			return "actors/deleteActorPhoto";
		}
		try {
			long photoId = form.getId();		
			actorServices.deletePhoto(photoId);
			return "actors/deleteActorPhotoSuccess";
		} catch (PhotoNotFoundException e) {
			result.rejectValue("id", "notFound", "photo nooot found");				
			return "actors/deleteActorPhoto";
		} 
	}
	
}
