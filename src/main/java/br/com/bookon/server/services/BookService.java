package br.com.bookon.server.services;

import br.com.bookon.server.models.User;
import br.com.bookon.server.payload.request.BookRequest;
import br.com.bookon.server.payload.response.BookResponse;
import br.com.bookon.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bookon.server.models.Book;
import br.com.bookon.server.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class BookService {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createBook(BookRequest bookRequest, Integer userId){

        Book book = bookRequest.build();
		
		Optional<User> user = userRepository.findById(userId);
		  if (user.isEmpty()) { 
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."); 
		  }
		  book.setUser(user.get());
		  
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(bookRepository.save(book)));
    }

    public ResponseEntity<List<?>> getAllBooks() {
    	List<Book> books = bookRepository.findAll();
    	List<BookResponse> bookResponses = books.stream()
	            .map(BookResponse::new)
	            .collect(Collectors.toList());
    	
        return ResponseEntity.status(HttpStatus.OK).body(bookResponses);
    }

    public Book getBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
