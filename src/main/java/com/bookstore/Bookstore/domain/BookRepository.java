package com.bookstore.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.Bookstore.model.Book;


	public interface BookRepository extends CrudRepository<Book, Long> {
		
		List<Book> findByTitle(String title);
}
