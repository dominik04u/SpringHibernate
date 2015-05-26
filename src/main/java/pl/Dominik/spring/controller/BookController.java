package pl.Dominik.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.Dominik.spring.model.Book;
import pl.Dominik.spring.service.BookService;

@Controller
public class BookController {

	private BookService bookService;
	
	@Autowired(required=true)
	@Qualifier(value="bookService")
	public void setBookService(BookService bookService){
		this.bookService=bookService;
	}
	
	@RequestMapping(value="/userprofile",method=RequestMethod.GET)
	public String about(Model model){
		model.addAttribute("bookForm", new Book());
		model.addAttribute("listBooks", bookService.listBooks());
		return "profile";
	}
	
	//dodawanie ksi¹¿ki
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public String processAddingBook(@Valid @ModelAttribute("bookForm")Book book,BindingResult result, Model model){
		if(!result.hasErrors())
		try{
			this.bookService.addBook(book);
		}catch (Exception e) {
			return "errorBook";
		}
		model.addAttribute("listBooks", bookService.listBooks());
		return "profile";
	}
	
	//usuwanie ksi¹¿ki
	@RequestMapping("/userRemoveBook/{id}")
	public String removeBook(@PathVariable("id") int id){
		this.bookService.removeBook(id);
		return "redirect:/userprofile";
	}
}
