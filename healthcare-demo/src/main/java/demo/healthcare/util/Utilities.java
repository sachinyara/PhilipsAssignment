package demo.healthcare.util;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Utilities {
	public static String calculateAge(Date dateOfBirth){
		if(dateOfBirth != null){
			Period period = Period.between(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			return Math.abs(period.getYears()) + " years " + Math.abs(period.getMonths()) + " months "+ Math.abs(period.getDays()) + " days";
		}
		return null;
	}
}
