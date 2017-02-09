package com.dub.site.advanced;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dub.site.validation.NotTooOld;

public class ActorMovieTransForm {
	
	@NotNull(message = "{validate.firstName.required}")
	@Size(min = 1, message = "{validate.firstName.required}")
	String firstName;
	
	@NotNull(message = "{validate.lastName.required}")
	@Size(min = 1, message = "{validate.lastName.required}")
	String lastName;
	
	@NotTooOld(message = "{validate.birthDate.remote}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@NotNull(message = "{validate.birthDate.required}")
	Date birthDate;
	
	@NotNull(message = "{validate.lastName.required}")
	@Size(min = 1, message = "{validate.lastName.required}")
	String title;
	
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@NotNull(message = "{validate.birthDate.required}")
	Date releaseDate;

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

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

}
