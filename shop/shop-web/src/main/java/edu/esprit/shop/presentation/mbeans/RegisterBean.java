package edu.esprit.shop.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import edu.esprit.shop.persistence.Customer;
import edu.esprit.shop.services.UserServiceLocal;

@ManagedBean( name = "register")
@RequestScoped
public class RegisterBean {
	
	@EJB
	private UserServiceLocal userServiceLocal;
	
	private Customer customer; 
	
	public RegisterBean() {
	}
	
	@PostConstruct
	public void init(){
		customer = new Customer();
	}
	
	public String doRegister(){
		String navigateTo = null;
		userServiceLocal.createUser(customer);
		FacesContext context = FacesContext.getCurrentInstance();
		AuthenticationBean auth = 
		context
		.getApplication()
		.evaluateExpressionGet(context, "#{auth}", AuthenticationBean.class);
		auth.setLogin(customer.getLogin());
		auth.setPassword(customer.getPassword());
		navigateTo = auth.doLogin();
		return navigateTo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void validateLoginUnicity(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String loginToValidate = (String) value;
		if (loginToValidate == null || loginToValidate.equals("")) {
			return;
		}
		Boolean loginInuse = userServiceLocal.loginExists(loginToValidate);
		if (loginInuse) {
			throw new ValidatorException(new FacesMessage("Login already in use"));
		}
		
	}
	
	
	
	

}
