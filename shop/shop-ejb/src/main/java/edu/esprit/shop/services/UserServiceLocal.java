package edu.esprit.shop.services;

import java.util.List;

import javax.ejb.Local;

import edu.esprit.shop.persistence.Customer;
import edu.esprit.shop.persistence.User;
@Local
public interface UserServiceLocal {
	void createUser(User user);
	void saveCustomer(Customer customer);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	boolean loginExists(String login);
	User findUserByLogin(String login);
}
