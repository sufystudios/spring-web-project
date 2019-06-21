package com.luv2code.springdemo.mvc;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {

	public List<Customer> getCustomers();
	public List<Advertiser> getAdvertisers();
	public void saveCustomer(Customer c);
	public void updateCustomer(Customer c);
	public void deleteReply(int rid);
}
