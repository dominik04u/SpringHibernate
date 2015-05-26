package pl.Dominik.spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import pl.Dominik.spring.dao.UserDAO;
import pl.Dominik.spring.exception.UserAlreadyExistsException;
import pl.Dominik.spring.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired//wstrzykuje po typie, gdy nie znajdzie, to ustawiany jest atrybut 'false'
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	
	public void addUser(User user) throws UserAlreadyExistsException{
		this.userDAO.addUser(user);
	}
	
	@Transactional
	public List<User> listUser(){
		return this.userDAO.listUsers();
	}
	
	@Transactional
	public void removeUser(int id){
		this.userDAO.removeUser(id);
	}
	
	@Transactional
	public User getUserById(int id){
		return this.userDAO.getUserById(id);
	}

	
	@Transactional
	public void updateUser(User user){
		this.userDAO.updateUser(user);
	}
	
	//listowanie
	public List<User> getPage(int pageNumber){
		return this.userDAO.getData(pageNumber);
	}
}
