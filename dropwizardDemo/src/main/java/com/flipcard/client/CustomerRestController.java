package com.flipcard.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flipcard.bean.Customer;

@Path("/customer")
public class CustomerRestController {
	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer veiwcustomer(){
		Customer cust = new Customer();
		cust.setId("101");
		cust.setName("Tejeshwar");
		return cust;
		
	}
}
