package edu.esprit.shop.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import edu.esprit.shop.persistence.Admin;
import edu.esprit.shop.persistence.Customer;
import edu.esprit.shop.persistence.User;
import edu.esprit.shop.services.UserServiceLocal;

@ManagedBean(name = "auth")
@RequestScoped
public class AuthenticationBean {

	@EJB
	private UserServiceLocal userServiceLocal;
	
	@ManagedProperty("#{identity}")
	private IdentityBean identityBean;

	private String login;
	private String password;

	public AuthenticationBean() {
	}

	public String doLogin() {
		String navigateTo = null;
		User found = userServiceLocal.authenticate(login, password);
		if (found != null) {
			identityBean.setUser(found);
			if (found instanceof Admin) {
				navigateTo = "/pages/admin/home?faces-redirect=true";
			} else if (found instanceof Customer) {
				navigateTo = "/pages/customer/home?faces-redirect=true";
			}
		} else {
			navigateTo = "/pages/login";
			FacesContext.getCurrentInstance()
			.addMessage("loginForm:submitButton", new FacesMessage("Bad Credentials"));
		}
		return navigateTo;
	}
	
	public String doLogout(){
		String navigateTo = null;
		FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.invalidateSession();
		navigateTo = "/pages/welcome?faces-redirect=true";
		return navigateTo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setIdentityBean(IdentityBean identityBean) {
		this.identityBean = identityBean;
	}

}
