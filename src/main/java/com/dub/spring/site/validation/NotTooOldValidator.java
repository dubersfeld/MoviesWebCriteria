package com.dub.spring.site.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

@Service
public class NotTooOldValidator implements ConstraintValidator<NotTooOld, Date> {
	
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	 
	private Date limDate; 

	
	@Override
	public void initialize(NotTooOld arg0) {
		try {
			limDate = formatter.parse("1920-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext context) {
		if (date.before(limDate)) {
			return false;
		} else { 				
			return true; 
		}
	}
		
}// class
