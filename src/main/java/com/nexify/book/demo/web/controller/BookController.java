package com.nexify.book.demo.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexify.book.demo.domain.Book;
import com.nexify.book.demo.dto.BookDto;
import com.nexify.book.demo.service.BookService;

@Controller
public class BookController {

	private static final String TEMPLATE_LIST_BOOK = "bookLandingPage";
	private static final String TEMPLATE_ADD_BOOK = "addBook";
	private static final String TEMPLATE_UPDATE_BOOK = "updateBook";

	private static final String ATT_BOOK = "books";
	private static final String ATT_ADD_BOOK_PAGE = "addBookPage";
	private static final String ATT_UPDATE_BOOK_PAGE = "updateBookPage";

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = {"/book","/",""}, method = RequestMethod.GET)
	public String landingPage(@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		//if (!model.containsAttribute(ATT_BOOK)) {
			model.addAttribute(ATT_BOOK,bookService.findAll());
		//}
		System.out.println(bookService.findAll());
		System.out.println(model);
		return TEMPLATE_LIST_BOOK;
	} 
	
	@PostMapping(value = { "/book" })
	public String bookList(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		System.out.println("37 " + _eventId_mode);
		if (_eventId_mode.equalsIgnoreCase("new")) {
			return TEMPLATE_ADD_BOOK;
		} else if (_eventId_mode.equalsIgnoreCase("update")) {
			System.out.println("50");
			return TEMPLATE_UPDATE_BOOK;
		} else if (_eventId_mode.equalsIgnoreCase("delete")) {
			System.out.println("53");
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
    @RequestMapping(value = {"/addBook"}, method = RequestMethod.GET)
	public String getAddBook(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto,Model model){
		System.out.println("46");
		if (!model.containsAttribute(ATT_ADD_BOOK_PAGE)) {
			model.addAttribute(ATT_ADD_BOOK_PAGE,bookDto);
		}
		return TEMPLATE_ADD_BOOK; 
	} 
    
	@PostMapping(value = { "/addBook" })
	public String getAddBook(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		System.out.println("56");
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
		return TEMPLATE_LIST_BOOK;
	}

	@PostMapping(path = "/updateBook")
	@ResponseBody
	public String createProduct(@RequestBody Book book){
	    bookService.save(book);
	    return "OK";
	}
	
    @GetMapping(value = {"/updateBook"})
	public String getUpdateBook(@RequestParam(value="id", required = false) Long id,@ModelAttribute(ATT_UPDATE_BOOK_PAGE)BookDto bookDto,Model model){
		System.out.println("97 " + bookDto + id);
		if (!model.containsAttribute(ATT_UPDATE_BOOK_PAGE)) {
			model.addAttribute(ATT_UPDATE_BOOK_PAGE,bookDto);
		}
		return TEMPLATE_UPDATE_BOOK; 
	} 
    
//	@PostMapping(value = { "/updateBook" })
//	public String getUpdateBook(@RequestParam (name ="id") Long id,@ModelAttribute(ATT_UPDATE_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
//			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
//		System.out.println("105");
//		if (_eventId_mode.equalsIgnoreCase("update")) {
//			Book book = new Book();
//			if (bookDto.getAuthor() != null){
//				if (!bookDto.getAuthor().isEmpty()){
//					book.setAuthor(bookDto.getAuthor());
//				}
//			}
//			
//			if (bookDto.getTitle() != null){
//				if (!bookDto.getTitle().isEmpty()){
//					book.setTitle(bookDto.getTitle());
//				}
//			}
//			bookService.save(book);
//		}
//		return TEMPLATE_LIST_BOOK;
//	}

	@RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
	public String deleteBook(@PathVariable(name = "id") Long id,@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		//if (!model.containsAttribute(ATT_BOOK)) {
			//model.addAttribute(ATT_BOOK,bookService.findAll());
		//}
		bookService.deleteById(id);
		return TEMPLATE_LIST_BOOK;
	} 

}
