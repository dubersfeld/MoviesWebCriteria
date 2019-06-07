package com.dub.spring.site.movies;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dub.spring.site.validation.NotTooOld;


public class TitleDateForm {
	
	
	@NotNull(message = "{validate.title.required}")
	@Size(min = 1, message = "{validate.title.required}")
	protected String title;
	
	//@NotTooOld(message = "{validate.releaseDate.remote}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@NotNull(message = "{validate.releaseDate.required}")
	protected Date releaseDate;
	
	
 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
		  

}// class
