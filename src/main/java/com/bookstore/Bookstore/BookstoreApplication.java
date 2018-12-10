package com.bookstore.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.Bookstore.domain.BookRepository;
import com.bookstore.Bookstore.domain.CategoryRepository;
import com.bookstore.Bookstore.model.Book;
import com.bookstore.Bookstore.domain.*;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository caterepo, UserRepository userepo) {
		return (args) -> {
			 /*
             * title, author, year, isbn, price
             * string, string, int, string, double
             */
			
			log.info("save a couple of books");
			
			Category fantasy = new Category("Fantasy");
			Category manga = new Category("Manga");
			Category action = new Category("Action");
			
			caterepo.save(fantasy);
			caterepo.save(manga);
			caterepo.save(action);
			
			repository.save(new Book("One piece", "Eiichiro Oda", 1998, "1-56931-901-4", 10.95, manga));
			repository.save(new Book("Altered Carbon", "Richard K. Morgan", 2002, "0-575-07321-7", 20.95, action));
			repository.save(new Book("Harry Potter", "J. K. Rowling", 2005, "0-7475-8108-8", 19.95, fantasy));
			
			//Create users (password = pass)
			User userN = new User("user", "$2a$10$ovXO51tKeLLkWIxZW2jX.e.EJ6UsHh09MrA8RcozWx9DBMbBC3xyS", "user.normal@hotmail.com", "USER");
			User userA = new User("admin", "$2a$10$3ss4HguyPHCoHAgajgaPPu5w76hJL7si5N1UBczytNL7C59zeHmjC", "user.admin@hotmail.com", "ADMIN");
			userepo.save(userN);
			userepo.save(userA);
			
			log.info("fetch all book");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
