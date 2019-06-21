package com.luv2code.springdemo.mvc;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author sufy
 */
@Entity
public class Responder extends Customer {

	@Transient
    Map<String, Advertiser> matches;
    public void setMatches(Map<String, Advertiser> matches) {
		this.matches = matches;
	}
    @Transient
	String message;
    Responder(Customers c) {
        super(c);
        matches = new HashMap<String, Advertiser>();
        setType("Responder");

    }
    Responder() {
        
        matches = new HashMap<String, Advertiser>();
        setType("Responder");

    }
    
    
    public void setMatches(HashMap<String, Advertiser> matches) {
		this.matches = matches;
	}
	@Override
	public String toString() {
		return "Responder [matches=" + matches + ", message=" + message + "]";
	}
	public String outputHTML() {
    	getMatches();
    	String message;
    	message="<ul class=\"list-group\">";
    	 for (Map.Entry<String, Advertiser> entry : matches.entrySet()) {
             message+="<li class=\"list-group-item\">";
    		 message+="User: " + entry.getValue().getLogin();
    		 message+="<br />Age: " + entry.getValue().getAge();
    		 message+="<br />Gender: " + entry.getValue().getGender();
    		 message+="<br />Income: " + entry.getValue().getIncome();
             message+="<br/>Advertisement: " + entry.getValue().getAdvertisement();
             message+="</li>";

         }
    	 message+="</ul>";
    	 this.message=message;
    	return message;
    }
    public void setMessage(String msg) {
    	this.message=msg;
    }
    public String getMessage() {
    	return message;
    }
    private void validateInput(String username, String gender, int age, int income) throws Exception {
        if (username == null) {
            throw new Exception("Username is null");

        }

        if (gender == null || ((!gender.equals("M") && !gender.equals("m") && !gender.equals("F") && !gender.equals("f")))) {
            throw new Exception("Invalid gender");
        }
        if (age < 16 || age > 150) {
            throw new Exception("Invalid age");
        }
        if (income < 1 || income > 800000000) {
            throw new Exception("Invalid income");
        }
    }

    public boolean createResponder(String username, String gender, int age, int income) {
        try {

            validateInput(username, gender, age, income);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        chooseUsername(username);
        setGender(gender);
        setAge(age);
        setIncome(income);
        getMatches();
        return true;

    }

    public boolean addReply(String advUser, String text) {
        //one reply per Responder for each advertiser
        if (matches.containsKey(advUser)) {
            if (!matches.get(advUser).replyExists(this)) {
                matches.get(advUser).addReply(text, this);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public HashMap<String, Advertiser> login(String user, String pw) {
        try {
            if (getLogin().equals(user)) {
                if (getPassword().equals(pw)) {
                    getMatches();
                    return (HashMap)matches;
                } else {
                    throw new Exception("Incorrect password");
                }
            } else {
                throw new Exception("Incorrect login");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Failed");
        return null;
    }

    public boolean isEqual(Advertiser v) {
        if (v != null) {

            if (getGender().equalsIgnoreCase(v.getReq().getGender())
                    && this.getAge() >= v.getReq().getlowage() && this.getAge() <= v.getReq().gethighage()
                    && this.getIncome() >= v.getReq().getlowinc() && this.getIncome() <= v.getReq().gethighinc()) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Advertiser> getMatches() {
        matches = new HashMap<String, Advertiser>();
        if (customers!=null) {
        if(customers.giveMap()!=null) {
        Iterator it = customers.giveMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (((Customer) pair.getValue()).getType().equals("Advertiser")) {
                if (isEqual((Advertiser) pair.getValue())) {
                    matches.put(((Customer) pair.getValue()).getLogin(), (Advertiser) pair.getValue());

                }
            }
           
        }
        }}
        return matches;

    }
}
