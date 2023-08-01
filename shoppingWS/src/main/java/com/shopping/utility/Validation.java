package com.shopping.utility;

public class Validation {

	public static boolean validateId(Integer id) {
		if(id!=null) {
			if(id.toString().matches("[0-9]{4}")) {
				return true;				
			}else {
				return false;
			}
		}
		else {
			return false;
		}	
	}
	
	
	public static boolean validateName(String name) {
		if(name!=null) {
			if(name.matches("[A-Za-z]{1,50}")) {
				return true;				
			}else {
				return false;
			}
		}
		else {
			return false;
		}	
	}
	
	public static boolean validateNumber(Integer number) {
		if(number!=null) {
			if(number.toString().matches("[0-9]+")) {
				return true;				
			}else {
				return false;
			}
		}
		else {
			return false;
		}	
	}
	
	public static boolean validateNumber(Float number) {
		if(number!=null) {
			if(number.toString().matches("[0-9.]+")) {
				return true;				
			}else {
				return false;
			}
		}
		else {
			return false;
		}	
	}
}
