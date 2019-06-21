package com.luv2code.springdemo.mvc;

public class Client {
	
	
	public static boolean login(Customers c,String username, String password) {
		HelperClass.createAdvertisers(c);
		 Customer cust=HelperClass.login(username,c);
		 if(cust==null) {
			 return false;
		 }
		 if(cust.getLogin().equals(username) && cust.getPassword().equals(password)) {
			 return true;
		 }
		 
		 return false;
		
	}
}