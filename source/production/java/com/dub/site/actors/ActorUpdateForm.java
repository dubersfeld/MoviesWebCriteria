package com.dub.site.actors;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.dub.site.validation.NotTooOld;

public class ActorUpdateForm {
	
	long id;
	String firstName;
	String lastName;
	
	@NotTooOld(message = "{validate.birthDate.remote}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@NotNull(message = "{validate.birthDate.required}")
	Date birthDate;

	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
