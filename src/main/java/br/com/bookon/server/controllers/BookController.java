package br.com.bookon.server.controllers;

import br.com.bookon.server.payload.request.BookRequest;
import br.com.bookon.server.payload.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.models.Book;
import br.com.bookon.server.services.BookService;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
    private BookService bookService;

    @PostMapping("/new/{user_id}")
    public BookResponse createBook(@RequestBody BookRequest book, @PathVariable int userId) {
        return new BookResponse(bookService.createBook(book, userId));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
