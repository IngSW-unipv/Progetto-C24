package it.unipv.ingsfw.gasCorpCinema.model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Payment {
	
	
	
	
	public boolean nameValidate(String input) {
		String regex = "^[a-zA-Z]{1,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
	}
	
	public boolean cardNumberValidate(String input) {
		String regex = "^\\d{16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
	}
	
	public boolean dateValidate(LocalDate expirationDate) {
		LocalDate today= LocalDate.now();
		boolean b = expirationDate.isAfter(today);
		return b;
	}
	
	
	
	
	
}
