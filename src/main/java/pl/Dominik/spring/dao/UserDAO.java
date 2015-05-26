package pl.Dominik.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.Dominik.spring.exception.UserAlreadyExistsException;
import pl.Dominik.spring.model.User;
import pl.Dominik.spring.model.UserRole;

@Repository
public class UserDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory=sf;
	}
	
	//dodawanie nowego u¿ytkownika do bazy
	public void addUser(User user) throws UserAlreadyExistsException{
		Session session=this.sessionFactory.getCurrentSession();
		boolean exists=session.createQuery("FROM User WHERE username = :param")
				.setParameter("param", user.getUsername()).list().size()>0;	
		if(exists)
				throw new UserAlreadyExistsException("U¿ytkownik o podanej nawzie ju¿ istnieje");
		boolean exists1=session.createQuery("FROM User WHERE email = :param")
				.setParameter("param", user.getEmail()).list().size()>0;
		if(exists || exists1)
				throw new UserAlreadyExistsException("U¿ytkownik o podanym emailu ju¿ istnieje");
		session.persist(user);
		UserRole userRole = new UserRole(user, "ROLE_USER");
		session.persist(userRole);
	}
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String username){
		List<User> users=new ArrayList<User>();
		
		users=sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE username=?")
				.setParameter(0, username).list();
		if(users.size()>0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public User findByEmail(String email){
		List<User> users=new ArrayList<User>();
		
		users=sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE email=?")
				.setParameter(0, email).list();
		if(users.size()>0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
	
	//lista u¿ytkowników
	@SuppressWarnings("unchecked")
	public List<User> listUsers(){
		Session session=this.sessionFactory.getCurrentSession();
		List<User> userList=session.createQuery("FROM User").list();
		return userList;
	}
	
	//usuwanie u¿ytkownika
	public void removeUser(int id){
		Session session=this.sessionFactory.getCurrentSession();
		User user=(User)session.load(User.class, new Integer(id));
		UserRole userRole=(UserRole) session.createCriteria(UserRole.class).add(Restrictions.eq("user",user)).uniqueResult();
		session.delete(userRole);
		if(user != null){
			session.delete(user);
		}
	}
	
	//pobieranie u¿ytkownika
	public User getUserById(int id){
		Session session=this.sessionFactory.getCurrentSession();
		User user=(User)session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
		return user;
	}


	//edycja u¿ytkownika
	public void updateUser(User u){
		Session session=this.sessionFactory.getCurrentSession();
		User user=(User) session.createCriteria(User.class).add(Restrictions.eq("id", u.getId())).uniqueResult();
		user.setEmail(u.getEmail());
		user.setUsername(u.getUsername());
		session.update(user);
	}
	
	//stronnicowanie
	private static int pageSize=5;
	
	@SuppressWarnings("unchecked")
	public List<User> getData(int pageNumber){
		Session session=this.sessionFactory.getCurrentSession();
		List<User> result=null;
		Query query=session.createQuery("FROM User");
		query.setFirstResult(pageSize*(pageNumber-1));
		query.setMaxResults(pageSize);
		result=query.list();
		return result;
	}
}
