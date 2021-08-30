package com.nexify.book.demo.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexify.book.demo.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
