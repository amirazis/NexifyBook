package com.nexify.book.demo.web.restcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexify.book.demo.domain.Book;
import com.nexify.book.demo.dto.BookDto;
import com.nexify.book.demo.service.BookService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

	private static final String ATT_BOOK = "books";
	private static final String TEMPLATE_LIST_BOOK = "bookLandingPage";
	private static final String ATT_ADD_BOOK_PAGE = "addBookPage";

	@Autowired
	private BookService bookService;
	
//	@GetMapping(value="")
//	public ResponseEntity<List<Book>> fetchAll() {
//		return ok().body(bookService.findAll());
//	}

	@RequestMapping("/new")
	void handleFoo(HttpServletResponse response) throws IOException {
		response.sendRedirect(ATT_ADD_BOOK_PAGE);
	}
	  
	@PostMapping(value = { "/add" })
	void getAddBook(@ModelAttribute(ATT_ADD_BOOK_PAGE)BookDto bookDto, HttpServletResponse response, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale, @RequestParam(required = false) String _eventId_mode) throws IOException {
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
		response.sendRedirect(TEMPLATE_LIST_BOOK);
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
		bookService.deleteById(id);
		return "redirect:/";
	} 
	
	@DeleteMapping(value = "/delete/{id}")
	public @ResponseBody ResponseEntity deleteBook(@PathVariable(name="id") Long id, @RequestParam UUID key) {

		Boolean isRemoved = bookService.deleteById(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
