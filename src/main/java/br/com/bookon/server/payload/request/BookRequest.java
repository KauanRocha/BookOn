package br.com.bookon.server.payload.request;

import br.com.bookon.server.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String category;

    @NotNull
    private Long userId;

    public String getBookTitle() {
        return title;
    }

    public void setBookTitle(String title) {
        this.title = title;
    }

    public String getBookAuthor() {
        return author;
    }

    public void setBookAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return category;
    }

    public void setBookCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Book build() {
    	Book book = new Book();
        book.setTitle(title);
    	book.setAuthor(author);
    	book.setCategory(category);
    	
    	return book;
    }
}
