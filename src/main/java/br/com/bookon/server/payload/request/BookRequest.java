package br.com.bookon.server.payload.request;

import br.com.bookon.server.models.postgre.Book;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String category;


    public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Book build() {
    	Book book = new Book();
        book.setTitle(title);
    	book.setAuthor(author);
    	book.setCategory(category);
    	
    	return book;
    }
}
