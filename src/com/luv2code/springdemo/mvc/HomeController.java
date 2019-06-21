package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@Autowired
	private CustomerDAO customerDAO;
	private Customers c;
    public List<Customer> getCusts() {
		SessionFactory sf=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Advertiser.class)
				.addAnnotatedClass(Requirements.class)
				.addAnnotatedClass(Replies.class)
				.addAnnotatedClass(Responder.class)
				.buildSessionFactory();
		Session session = sf.getCurrentSession();
		List<Responder> list2=new ArrayList<Responder>();
		List<Advertiser> list=new ArrayList<Advertiser>();
		try {
		session.beginTransaction();
		list=session.createQuery("from Advertiser").getResultList();
		session.getTransaction().commit();
		session.close();
		session=sf.getCurrentSession();
		session.beginTransaction();
		list2=session.createQuery("from Responder").getResultList();
		session.close();
		} catch(Exception e) {
			e.printStackTrace(System.out);
		} finally {
			session.close();
			sf.close();
		}
		List<Customer> custs=new ArrayList();
		custs.addAll(list);
		custs.addAll(list2);
		return custs;
    }
	@RequestMapping("/")
    public String greeting (Model theModel){
		if(c==null)
	        c=new Customers();

		c.setCustMap(getCusts());
	
		HelperClass.outputLoginPassword(c.giveMap());
        User user = new User();
        Responder responder= new Responder(c);
        Advertiser advertiser=new Advertiser(new Requirements());
    
        theModel.addAttribute(user);
        theModel.addAttribute(responder);
        theModel.addAttribute(advertiser);
    
        

        return "greeting";
    }
	@RequestMapping("/list") 
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers=getCusts();
		theModel.addAttribute("customer",theCustomers);
		return "list-customers";
		
	}
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,Model theModel) {
    	System.out.println(theBindingResult);
    	System.out.println(theUser.getUsername());
    	c.setCustMap(getCusts());
	
    	
   
    	if(theBindingResult.hasErrors()) {
    			
    	        Responder responder= new Responder(c);
    	        Advertiser advertiser=new Advertiser(new Requirements());
    	    
    	        theModel.addAttribute("user",theUser);
    	        theModel.addAttribute(responder);
    	        theModel.addAttribute(advertiser);
    		return "greeting";
    	}
    	else {
    	System.out.println( c);
    	if (Client.login(c, theUser.getUsername(),theUser.getPassword()))	{
    		String username=theUser.getUsername();
    		Customer customer=c.getCustomer(username);
    		customer.setCustomers(c);
    		if(customer.getType().equals("Advertiser"))
    		{	
    			Advertiser a=(Advertiser)customer;
    			a.outputHTML();
    			ArrayList<String> message=a.getMessage();
    			theModel.addAttribute("replies",a.getReplies());
    			theModel.addAttribute((Advertiser)customer);
    			theModel.addAttribute("message",message);
    			return "advertiser";
    		}
    		if(customer.getType().equals("Responder")) {
    			Responder responder=(Responder)customer;
    			responder.getMatches();
    			
    		
    		String message=responder.outputHTML();
    		Message reply=new Message();
    		theModel.addAttribute(responder);
    		theModel.addAttribute(message);
    		theModel.addAttribute("reply",reply);
    		System.out.println(message);
    		return "responder";
    		}
    	}}
    	return "error";

    }
    @RequestMapping("/submitreply")
    public String submitReply(Model theModel, @ModelAttribute("reply") Message msg) {
    	if(msg!=null) {
    	
    		c.setCustMap(getCusts());
    		if(c.getCustomer(msg.getUsername()).getType().equals("Advertiser")) {
    		Advertiser a =(Advertiser)c.getCustomer(msg.getUsername());
    		a.addReply(msg.getMessage(), (Responder)c.getCustomer(msg.getResponder()));
    		customerDAO.updateCustomer((Customer)a);
    		return "submitted";
    		}
    	}
    	return "error";
    	
   
    }
    @RequestMapping("/createUserResponder") 
    public String createUser(@Valid @ModelAttribute("responder") Responder res,BindingResult theBindingResult,Model theModel) {
    	c.setCustMap(getCusts());
    	boolean logintaken=c.loginTaken(res.getLogin());
    	if(!logintaken) {
    	if(!theBindingResult.hasErrors()) {
    	c.addCustomer(res,res.getLogin());
    		res.setCustomers(c);
    		res.getMatches();
    		User user=new User();
    		customerDAO.saveCustomer(res);
    		theModel.addAttribute(user);
    		theModel.addAttribute("responder",res);
    		return "createUserResponder";
    	}
    	}
    	theModel.addAttribute(res);
    	User user = new User();
    	Advertiser adv = new Advertiser();
    	if(logintaken)
    	adv.setLogin("Login already taken");
    	theModel.addAttribute(user);
    	theModel.addAttribute(adv);
    	return "greeting";
    
}
    @RequestMapping("/createUserAdvertiser") 
    public String createUser(@Valid @ModelAttribute("advertiser") Advertiser adv, BindingResult theBindingResult,Model theModel) {
    	System.out.println(theBindingResult);
    	c.setCustMap(getCusts());
    	boolean logintaken=c.loginTaken(adv.getLogin());
    	
    	if(!logintaken) {
    	if(!theBindingResult.hasErrors()) {
    		
    	
    	
    	System.out.println(adv.getReq().getGender());
    	c.addCustomer(adv,adv.getLogin());
    		adv.setCustomers(c);
    		
    		User user=new User();
    	
    		
    		customerDAO.saveCustomer(adv);
    		
    		theModel.addAttribute(user);
    		theModel.addAttribute("advertiser",adv);
    		return "createUserAdvertiser";
    	}}
    	if(logintaken)
    	adv.setLogin("Login already taken");
    	theModel.addAttribute(adv);
    	theModel.addAttribute(new User());
    	theModel.addAttribute(new Responder());
    	return "greeting";
    }
    	@RequestMapping("/updateAdvertiser") 
        public String updateAdvertiser(@Valid @ModelAttribute("advertiser") Advertiser adv, BindingResult theBindingResult,Model theModel) {
        	System.out.println(theBindingResult);
        	c.setCustMap(getCusts());
        	
        	Advertiser a=(Advertiser)c.giveMap().get(adv.getLogin());
        	a.setCustomers(c);
        	
        	if(!theBindingResult.hasErrors()) {
        		
        		
        		  a.setReq(adv.getReq());
        		  a.setPassword(adv.getPassword());
        		  a.setAge(adv.getAge());
        		  a.setIncome(adv.getIncome());
        		  a.setAdvertisement(adv.getAdvertisement());
        		  a.setGender(adv.getGender());
        		  customerDAO.updateCustomer(a);
        		  
        	
        	}
        	a.outputHTML();
			ArrayList<String> message=a.getMessage();
			theModel.addAttribute("replies",a.getReplies());
        	theModel.addAttribute("message",message);
        	theModel.addAttribute("advertiser",adv);
        	return "advertiser";
}
    	@RequestMapping("/updateResponder") 
        public String updateResponder(@Valid @ModelAttribute("responder") Responder res, BindingResult theBindingResult,Model theModel) {
        	System.out.println(theBindingResult);
        	c.setCustMap(getCusts());
        	Responder r=(Responder)c.giveMap().get(res.getLogin());
        	r.setCustomers(c);
        	if(!theBindingResult.hasErrors()) {
        		
        		r.setPassword(res.getPassword());
        		r.setGender(res.getGender());
        		r.setIncome(res.getIncome());
        		r.setAge(res.getAge());
        		theModel.addAttribute(r);
        		String message=r.outputHTML();
        		Message reply=new Message();
        		customerDAO.updateCustomer(r);
        		theModel.addAttribute(message);
        		theModel.addAttribute("reply",reply);
        		return "responder";
        		
        	}
        	res.setMatches(r.getMatches());
        	theModel.addAttribute("responder", res);
        	String message=r.outputHTML();
    		Message reply=new Message();
    		
    		theModel.addAttribute(message);
    		theModel.addAttribute("reply",reply);
        	return "responder";
}

@RequestMapping("/deleteReply")
public String deleteReply(@RequestParam(value="login") String login,@RequestParam(value="id") int id,@ModelAttribute("advertiser") Advertiser adv,Model theModel) {
	
	customerDAO.deleteReply(id);
	c.setCustMap(getCusts());
	Advertiser a=(Advertiser)c.giveMap().get(login);
	a.setCustomers(c);
	ArrayList<String> message=a.getMessage();
	theModel.addAttribute("replies",a.getReplies());
	theModel.addAttribute("message",message);
	theModel.addAttribute("advertiser",a);
	return "advertiser";
}
}
