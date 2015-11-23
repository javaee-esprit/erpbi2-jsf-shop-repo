package edu.esprit.shop.presentation.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.esprit.shop.persistence.Admin;
import edu.esprit.shop.persistence.Customer;
import edu.esprit.shop.persistence.User;


@ManagedBean( name = "identity" )
@SessionScoped
public class IdentityBean {

	private User user;
	
	public IdentityBean() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Boolean hasRole(String role){
		Boolean response = false;
		switch (role) {
		case "Admin":
			response = user instanceof Admin;
			break;
		case "Customer":
			response = user instanceof Customer;
			break;
		}
		return response;
	}

}
