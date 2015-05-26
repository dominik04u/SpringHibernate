package pl.Dominik.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import pl.Dominik.spring.model.Book;
import pl.Dominik.spring.model.User;

@Repository
public class BookDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	//dodawanie ksi¹¿ki
	public void addBook(Book book){
		Session session=this.sessionFactory.getCurrentSession();
		String username=((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user=(User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		book.setUser(user);
		session.persist(book);
	}
	
	//wypisanie listy ksi¹¿ek
	@SuppressWarnings("unchecked")
	public List<Book> listBooks(){
		Session session=this.sessionFactory.getCurrentSession();
		String username=((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user=(User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		List<Book> booksList=session.createCriteria(Book.class).add(Restrictions.eq("user",user)).list();
		return booksList;
	}
	
	//usuwanie ksi¹¿ki
	public void removeBook(int id){
		Session session=this.sessionFactory.getCurrentSession();
		Book book=(Book) session.load(Book.class, new Integer(id));//pobieranie ksi¹¿ek u¿ytkownika
		if(book!=null){
			session.delete(book);
		}
	}
}
