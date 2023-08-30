package br.com.bookon.server.services;

import br.com.bookon.server.models.User;
import br.com.bookon.server.payload.request.BookRequest;
import br.com.bookon.server.repository.UserRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import br.com.bookon.server.models.Book;
import br.com.bookon.server.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public void Save(Book book, int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
//          ("Usuário não encontrado com o ID: " + userId)
            throw new Error();
        }

        book.setUser(user.get());
        bookRepository.save(book);
    }
    public Book createBook(BookRequest bookRequest, int userId) {
        Book book = bookRequest.build();
        Save(book, userId);

        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
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
