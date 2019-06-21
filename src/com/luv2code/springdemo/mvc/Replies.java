package com.luv2code.springdemo.mvc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author sufy
 */
@Entity
@Table(name="replies")
public class Replies {
	@Column(name="message")
    private String message;
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private Advertiser receiver;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	public Replies() {
		
	}
    public int getId() {
		return id;
	}
    public Replies(Advertiser a) {
    	this.receiver=a;
    }

	public Advertiser getReceiver() {
		return receiver;
	}
	public void setReceiver(Advertiser receiver) {
		this.receiver = receiver;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Responder getOwner() {
		return owner;
	}

	public void setOwner(Responder owner) {
		this.owner = owner;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "login")
    private Responder owner;

    Replies(String message, Responder owner) {
        this.message = message;
        this.owner = owner;
    }

    public Responder getResponder() {
        return owner;
    }

    public String getMessage() {
        return message;
    }
}
