package edu.esprit.shop.services;

import java.util.List;

import javax.ejb.Remote;

import edu.esprit.shop.persistence.Customer;
import edu.esprit.shop.persistence.User;


@Remote
public interface UserServiceRemote {
	void createUser(User user);
	void saveCustomer(Customer customer);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	User findUserByLogin(String login);
}
