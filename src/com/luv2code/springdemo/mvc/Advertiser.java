package com.luv2code.springdemo.mvc;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sufy
 */
@Entity
public class Advertiser extends Customer {
	@NotNull(message="required")
	@NotBlank(message="required")
	@Column(name="advertisement")
private String advertisement;
   @Valid
   @OneToOne(cascade=CascadeType.ALL)
   private Requirements req;
    
  
    
    public void deleteReply(String username) throws Exception {
        if(replies!=null) {
           
            for(int i=0;i<replies.size();i++) {
                if(replies.get(i).getResponder().getLogin().equals(username)) {
                    replies.remove(i);
                    
                    return;
                }
            }
            
                throw new Exception("reply not found with username: " + username);
        }
        throw new Exception("No replies");
    }
    @OneToMany(fetch = FetchType.EAGER,mappedBy="receiver",cascade=CascadeType.ALL) 
    private List<Replies> replies;
    @Transient
	private ArrayList<String> message;
    public void setMessage(ArrayList<String> message) {
    	this.message=message;
    }
    Advertiser(Requirements requirements) {
    	req=requirements;
    }
    public ArrayList<String> getMessage() {
    	outputHTML();
    	return message;
    }


    Advertiser(Customers c) {
        super(c);
        setType("Advertiser");
        replies = new ArrayList<Replies>();
    }
    public Advertiser() {
    	setType("Advertiser");
    	replies=new ArrayList<Replies>();
    }
    public void outputHTML() {
   
    	message=new ArrayList<String>();
    
    	for(int i=0;i<replies.size();i++) {
    		message.add("<li class=\"list-group-item\">Username: " + replies.get(i).getResponder().getLogin() +
    				"<br />Message: " + replies.get(i).getMessage() + "</li>");
    	}
    	
    	
    }
    public void addReq(Requirements req) {
        this.req = req;

    }
    public void setReq(Requirements req) {
    	this.req=req;
    }

    public Requirements getReq() {
        return req;
    }

    public void setAdvertisement(String ad) {
        advertisement = ad;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    private void validateInput(String username, String advertisement, Requirements req, String gender, int age, int income) throws Exception {
        if (username == null) {
            throw new Exception("Username is null");

        }
        if (advertisement == null) {
            throw new Exception("Advertisement is null");

        }
        req.containsNull();

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

    public boolean createAdvertiser(String username, String advertisement, Requirements req, String gender, int age, int income) throws Exception {
        try {
            validateInput(username, advertisement, req, gender, age, income);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        chooseUsername(username);
        setGender(gender);
        setAge(age);
        setIncome(income);
        setAdvertisement(advertisement);
        addReq(req);
        return true;

    }

    public List<Replies> login(String user, String pw) {
        try {
            if (getLogin().equals(user)) {
                if (getPassword().equals(pw)) {
                    return getReplies();
                } else {
                    throw new Exception("Incorrect password");
                }
            } else {
                throw new Exception("Incorrect login");
            }
        } catch (Exception e) {
            System.out.println("Login Exception" + e);
        }
        return null;
    }

    public void addReply(String message, Responder r) {
    	Replies rep=new Replies(this);
    	rep.setMessage(message);
    	rep.setOwner(r);
        replies.add(rep);
    }

    public boolean replyExists(Responder r) {
        for (int i = 0; i < replies.size(); i++) {
            if (r == replies.get(i).getResponder()) {
                return true;
            }
        }
        return false;
    }

    public List<Replies> getReplies() {
        return replies;
    }

	public void setReplies(List<Replies> replies) {
		this.replies = replies;
	}

}
