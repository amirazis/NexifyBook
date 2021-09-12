package com.nexify.book.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.nexify.book.demo.domain.Book;

public interface BookService {

//	DataTablesOutput<Book> findAll(DataTablesInput input);
	Book save (Book book);
	List<Book> findAll();
	Optional<Book> findById(Long id);
	Boolean deleteById(Long id);
}
