package com.nexify.book.demo.web.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexify.book.demo.domain.Book;
import com.nexify.book.demo.dto.BookDto;
import com.nexify.book.demo.service.BookService;

@Controller
@RequestMapping({ "/book","/","" })
public class BookController {

	private static final String TEMPLATE_LIST_BOOK = "bookLandingPage";
	private static final String TEMPLATE_ADD_BOOK = "addBook";
	private static final String TEMPLATE_UPDATE_BOOK = "updateBook";

	private static final String ATT_BOOK = "books";
	private static final String ATT_ADD_BOOK_PAGE = "addBookPage";
	private static final String ATT_UPDATE_BOOK_PAGE = "updateBookPage";

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String landingPage(@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		model.addAttribute(ATT_BOOK,bookService.findAll());
		return TEMPLATE_LIST_BOOK;
	} 
	
	@PostMapping(value = { "/","" })
	public String bookList(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		if (_eventId_mode.equalsIgnoreCase("new")) {
			return TEMPLATE_ADD_BOOK;
		} else if (_eventId_mode.equalsIgnoreCase("edit")) {
			return TEMPLATE_UPDATE_BOOK;
		} else if (_eventId_mode.equalsIgnoreCase("delete")) {
			return TEMPLATE_LIST_BOOK;
		} else {
			return "redirect:/";
		}
	}
	
    @RequestMapping(value = {"/addBook"}, method = RequestMethod.GET)
	public String getAddBook(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto,Model model){
		if (!model.containsAttribute(ATT_ADD_BOOK_PAGE)) {
			model.addAttribute(ATT_ADD_BOOK_PAGE,bookDto);
		} 
		return TEMPLATE_ADD_BOOK; 
	} 
    
	@PostMapping(value = { "/addBook" })
	public String getAddBook(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		if (_eventId_mode.equalsIgnoreCase("save")) {
			Book book = new Book();
			if (bookDto.getAuthor() != null){
				if (!bookDto.getAuthor().isEmpty()){
					book.setAuthor(bookDto.getAuthor());
				}
			}
			if (bookDto.getTitle() != null){
				if (!bookDto.getTitle().isEmpty()){
					book.setTitle(bookDto.getTitle());
				}
			}
			bookService.save(book);
		}
		return "redirect:/";
	}
	
    @GetMapping(value = {"/updateBook/{id}"})
	public String getUpdateBook(@RequestParam(value="id", required = false) Long id,@ModelAttribute(ATT_UPDATE_BOOK_PAGE)BookDto bookDto,Model model){
		Optional<Book> bookEntity = bookService.findById(bookDto.getId());
		if (bookEntity.isPresent()) {
			Book book = bookEntity.get();
			bookDto.setId(book.getId());
			bookDto.setAuthor(book.getAuthor());
			bookDto.setTitle(book.getTitle());
		}
		if (!model.containsAttribute(ATT_UPDATE_BOOK_PAGE)) {
			model.addAttribute(ATT_UPDATE_BOOK_PAGE,bookDto);
		}
		model.addAttribute("id", bookDto.getId());
		return TEMPLATE_UPDATE_BOOK; 
	} 
    
    @RequestMapping(value = { "/updateBook/{id}" }, method = RequestMethod.POST)
	public String getUpdateBook(@ModelAttribute(ATT_UPDATE_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		if (_eventId_mode.equalsIgnoreCase("update")) {
			Book book = new Book();
			if (bookDto.getId() != null){
					book.setId(bookDto.getId());
			}
			
			if (bookDto.getAuthor() != null){
				if (!bookDto.getAuthor().isEmpty()){
					book.setAuthor(bookDto.getAuthor());
				}
			}
			
			if (bookDto.getTitle() != null){
				if (!bookDto.getTitle().isEmpty()){
					book.setTitle(bookDto.getTitle());
				}
			}
			bookService.save(book);
		}
		return "redirect:/";
	}

	@GetMapping(value = {"/delete/{id}"})
	public String getDeleteBook(@PathVariable(name = "id") Long id,@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		Optional<Book> bookEntity = bookService.findById(id);
		if (bookEntity.isPresent()) {
			Book book = bookEntity.get();
			bookDto.setId(book.getId());
			bookDto.setAuthor(book.getAuthor());
			bookDto.setTitle(book.getTitle());
		}
		if (!model.containsAttribute(ATT_BOOK)) {
			model.addAttribute(ATT_BOOK,bookDto);
		}
		model.addAttribute("id", id);
		System.out.println("here "+bookDto);
		return "redirect:/book";
	} 
	
	@RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.POST)
	public String postDeleteBook(@PathVariable(name = "id") Long id,@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		//if (!model.containsAttribute(ATT_BOOK)) {
			//model.addAttribute(ATT_BOOK,bookService.findAll());
		//}
		bookService.deleteById(id);System.out.println("148");
		return TEMPLATE_LIST_BOOK;
	} 

}
