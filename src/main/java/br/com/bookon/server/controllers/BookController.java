package br.com.bookon.server.controllers;

import br.com.bookon.server.annotations.UserId;
import br.com.bookon.server.models.postgres.Book;
import br.com.bookon.server.payload.request.postgres.BookRequest;
import br.com.bookon.server.payload.response.postgres.RegionWithBookRosponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.services.BookService;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookRequest bookRequest, @UserId Integer userId){
    	return bookService.createBook(bookRequest, userId);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
    	return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    
    @PostMapping("/find")
    public List<RegionWithBookRosponse> getBookByGeolocation(@UserId Integer userId){
    	return bookService.findRegionsWithNearbyBooks(userId);
    }
    
    @PostMapping("/jtw")
    public void createBook2(@UserId Integer userId){
    	
    	 System.out.println(userId);
    }
    
}
