package pl.Dominik.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import pl.Dominik.spring.dao.BookDAO;
import pl.Dominik.spring.model.Book;

@Service //widok klienta z us³ugi Web
@Transactional
public class BookService {

	@Autowired
	private BookDAO bookDAO;
	
	public void setBookDAO(BookDAO bookDAO){
		this.bookDAO=bookDAO;
	}
	
	public void addBook(Book book){
		this.bookDAO.addBook(book);
	}
	
	public List<Book> listBooks(){
		return this.bookDAO.listBooks();
	}
	
	public void removeBook(int id){
		this.bookDAO.removeBook(id);
	}
}
