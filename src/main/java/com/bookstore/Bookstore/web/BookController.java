package com.bookstore.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.Bookstore.domain.BookRepository;
import com.bookstore.Bookstore.domain.Category;
import com.bookstore.Bookstore.domain.CategoryRepository;
import com.bookstore.Bookstore.model.Book;

@Controller
public class BookController {
		
	@Autowired
	private BookRepository bookrepo;
	
	@Autowired
	private CategoryRepository caterepo;
	
	
	  public Book findBookById(long id) {
		  return bookrepo.findById(id).get();
	  }
	    
	  @RequestMapping(value="/login")
	  public String login() {
		  return "login";
	  }
		
	
	  @RequestMapping(value="/booklist")
	    public String booklist(Model model) {
		  	model.addAttribute("books", bookrepo.findAll());
	        return "booklist";
	  }
	  
	  // RESTful service to get all books
	  @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) bookrepo.findAll();
	  }   
	  
	  //RESTful service to get book by id
	  @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	  	public @ResponseBody Optional<Book> findBook(@PathVariable("id") Long bookId) {
		  return bookrepo.findById(bookId);
	  }
	  
	  //RESTful service to get all categories with books
	  @RequestMapping(value="/category", method = RequestMethod.GET)
	  	public @ResponseBody List<Category> categoryListRest() {
		  return (List<Category>) caterepo.findAll();
	  }
	  
	  @RequestMapping(value="/add")
	  	public String addBook(Model model) {
		  	model.addAttribute("book", new Book());
		  	model.addAttribute("categorys", caterepo.findAll());
		  	return "addbook";
	  }
	  
	  @RequestMapping(value="/save", method = RequestMethod.POST)
	  	public String saveBook(Book book) {
		  	bookrepo.save(book);
		  	return "redirect:booklist";
	  }
	  
	  @PreAuthorize("hasAuthority('ADMIN')")
	  @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	  	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		  bookrepo.deleteById(bookId);
		  return "redirect:../booklist";
	  }
	  
	  @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	  	public String editBook(@PathVariable("id") Long bookId, Model model) {
		  model.addAttribute("book", bookrepo.findById(bookId));
		  model.addAttribute("categorys", caterepo.findAll());
		  return "addbook";
	  }

}
