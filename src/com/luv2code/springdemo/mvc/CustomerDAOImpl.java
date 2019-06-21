package com.luv2code.springdemo.mvc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class CustomerDAOImpl implements CustomerDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional(readOnly=true)
	public List<Advertiser> getAdvertisers() {
		
		Session currentSession=sessionFactory.getCurrentSession();
		Query<Advertiser> thequery=currentSession.createQuery("from Advertiser", Advertiser.class);
		System.out.println(thequery.getFetchSize());
		List<Advertiser> advertisers=thequery.getResultList();
		System.out.println(advertisers.size());
	
		
		return advertisers;
	
	
		
	
	}
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		Session currentSession=sessionFactory.getCurrentSession();
	
		List<Advertiser> advertisers=currentSession.createQuery("from Advertiser", Advertiser.class).list();
		List<Responder> responders=currentSession.createQuery("from Responder",Responder.class).list();
	
		System.out.println(advertisers.size());
	
		
		List<Customer> customers= new ArrayList<Customer>();
		customers.addAll(advertisers);
		customers.addAll(responders);
		
		return customers;
		
	
	}
	@Override
	@Transactional
	public void saveCustomer(Customer c) {
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		if(c.getType().equals("Advertiser")) {
			currentSession.save((Advertiser)c);
			
		}
		else if(c.getType().equals("Responder")) {
			currentSession.save((Responder)c);
		}

		return;
	}
	@Override
	@Transactional
	public void updateCustomer(Customer c) {
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		if(c.getType().equals("Advertiser")) {
			currentSession.update((Advertiser)c);
			
		}
		else if(c.getType().equals("Responder")) {
			currentSession.update((Responder)c);
		}

		return;
	}
	
	@Override
	@Transactional
	public void deleteReply(int rid) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery= session.createQuery("delete from Replies where id=:replyid");
		theQuery.setParameter("replyid",rid);
		theQuery.executeUpdate();
		
		
	}

}
