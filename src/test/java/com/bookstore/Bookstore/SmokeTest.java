package com.bookstore.Bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.Bookstore.web.BookController;
import com.bookstore.Bookstore.web.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private BookController bookC;
	
	@Autowired
	private UserController userC;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(bookC).isNotNull();
		assertThat(userC).isNotNull();

	}

}
