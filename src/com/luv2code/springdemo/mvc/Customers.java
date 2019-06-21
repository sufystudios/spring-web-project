package com.luv2code.springdemo.mvc;

/**
 *
 * @author sufy
 */
import java.util.*;

public class Customers {

    private HashMap<String, Customer> custmap;
   public void setCustMap(List<Customer> list) {
	   custmap=new HashMap<String,Customer>();
	   for(int i=0;i<list.size();i++) {
		   custmap.put(list.get(i).getLogin(), list.get(i));
		   System.out.println(list.get(i).getLogin());
	   }
   }
    Customers() {
        custmap = new HashMap<String, Customer>();
       
    }

    public boolean loginTaken(String login) {
        if (custmap.containsKey(login)) {
            return true;
        } else {
            return false;
        }
    }

    public void addCustomer(Customer c, String username) {
        System.out.println("Adding customer " + username);
        custmap.put(username, c);
    }

    public Customer getCustomer(String username) {
        if (custmap.containsKey(username)) {
            return custmap.get(username);
        } else {
            return null;
        }
    }

    public HashMap<String, Customer> giveMap() {
        return custmap;
    }

    public boolean deleteCustomer(String username) {
        if (custmap.containsKey(username)) {
            custmap.remove(username);
            return true;
        }
        return false;
    }

    public void remove(String username) {
        deleteCustomer(username);
    }
}
