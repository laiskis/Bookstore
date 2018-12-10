package com.bookstore.Bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.Bookstore.domain.BookRepository;
import com.bookstore.Bookstore.domain.Category;
import com.bookstore.Bookstore.domain.CategoryRepository;
import com.bookstore.Bookstore.model.Book;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository bookrepo;
	
	@Autowired 
	private CategoryRepository caterepo;
	
	
	/*
     * title, author, year, isbn, price, category
     * string, string, int, string, double, Category
     */
	
	@Test
	public void createNewBook() {
		Category testCate = new Category();
		caterepo.save(testCate);
		
		Book book = new Book("TestTitle1", "TestAuthor1", 2000, "123-123123", 20.00, testCate);
		bookrepo.save(book);
		
		assertThat(book.getId()).isNotNull();
		assertThat(book.getTitle()).isEqualTo("TestTitle");
		
	}
	
	@Test
	public void deleteNewBook() {
		Category testCate = new Category();
		caterepo.save(testCate);
		
		Book book = new Book("TestTitle2", "TestAuthor2", 2000, "123-123123", 20.00, testCate);
		bookrepo.save(book);
		
		List<Book> books = bookrepo.findByTitle("TestTitle2");
		bookrepo.delete(books.get(0));
		List<Book> newBooks = bookrepo.findByTitle("TestTitle2");
		
		assertThat(newBooks).hasSize(0);
	}
	
	@Test
	public void searchNewBook() {
		List<Book> books = bookrepo.findByTitle("Harry Potter");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("J. K. Rowling");
	}
	
	@Test
	public void createNewCategory() {
		
		Category category = new Category("Dark Fantasy");
		caterepo.save(category);
		
		assertThat(category.getCategoryid()).isNotNull();
	}
	
}
