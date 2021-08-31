package com.nexify.book.demo.web.restcontroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexify.book.demo.service.BookService;

@RestController
//@RequestMapping("/api/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@DeleteMapping(value = "/delete/{id}")
	public @ResponseBody String deleteBook(@PathVariable(name="id") Long id, @RequestParam UUID key) {
		if (id != null) {
			bookService.deleteById(id);
		}
		return null;
	}
}
