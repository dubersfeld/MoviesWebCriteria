package com.dub.spring.site.movies;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class TitleForm {
	
	
	@NotNull(message = "{validate.title.required}")
	@Size(min = 1, message = "{validate.title.required}")
	protected String title;
	
	
 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	  

}// class
