package com.nexify.book.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexify.book.demo.domain.Book;
import com.nexify.book.demo.repository.BookRepository;
import com.nexify.book.demo.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
//	@Override
//	public DataTablesOutput<Book> findAll(DataTablesInput input) {
//		// TODO Auto-generated method stub
//		return bookRepository.findAll(input);
//	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public Book deleteById(Long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
		return null;
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

}
