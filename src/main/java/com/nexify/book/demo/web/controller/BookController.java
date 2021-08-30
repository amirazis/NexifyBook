package com.nexify.book.demo.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.nexify.book.demo.dto.BookDto;

@Controller
public class BookController {

	private static final String TEMPLATE_ADD_BOOK = "addBook";

	private static final String ATT_BOOK = "books";
	private static final String ATT_ADD_BOOK_PAGE = "addBookPage";

	@RequestMapping(value = {"/book","/",""}, method = RequestMethod.GET)
	public String landingPage(@ModelAttribute(ATT_BOOK)BookDto bookDto,Model model){
		if (!model.containsAttribute(ATT_BOOK)) {
			model.addAttribute(ATT_BOOK,bookDto);
		}
		System.out.println("30");
		return "/bookLandingPage.html";  
	} 
	
	@PostMapping(value = { "/book" })
	public String bookList(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) {
		System.out.println("37 " + _eventId_mode);
		if (_eventId_mode.equals("new")) {
			return "redirect:/addBook";
		}
		return "redirect:/";
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
//		if (_eventId_mode.equals("save")) {
//			return "redirect:/addBook";
//		}
		return TEMPLATE_ADD_BOOK;
	}

}
