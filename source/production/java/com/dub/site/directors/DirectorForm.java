package com.dub.site.directors;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dub.site.validation.NotTooOld;

public class DirectorForm {
	
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
	
}
