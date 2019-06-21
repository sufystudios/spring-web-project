package com.luv2code.springdemo.mvc;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Advertiser.class)
				.addAnnotatedClass(Requirements.class)
				.addAnnotatedClass(Replies.class)
				.addAnnotatedClass(Responder.class)
				.buildSessionFactory();

// create session
Session session=factory.openSession();

try {

	Advertiser a=new Advertiser();
	Customers c=new Customers();
	
	a.setLogin("kelly6");
	c.addCustomer(a, "kelly5");
	a.setAdvertisement("hi i'm kelly");
	a.setAge(32);
	a.setGender("f");
	a.setIncome(20000);
	a.setPassword("fred");
	a.setReq(new Requirements("m",16,35,1,60000));
	a.setCustomers(c);
Responder r=new Responder();
r.setLogin("pete5");
c.addCustomer(r, "pete5");
r.setGender("m");
r.setPassword("fred");
r.setIncome(30000);
r.setAge(32);
r.setCustomers(c);
System.out.println(c.giveMap());
r.getMatches();
r.addReply("kelly6", "hi how are you");
System.out.println(a.getReplies().get(0));
session.beginTransaction();
session.save(r);
session.getTransaction().commit();
session.close();
session=factory.openSession();
session.beginTransaction();
session.save(a);
session.close();
	} catch(Exception e) {
		
		
	} finally {
		session.close();
		factory.close();
	}

}
}
