package br.com.bookon.server.payload.response.mongo;

import br.com.bookon.server.models.mongo.Book;

public class BookResponse {

	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public Long getId() {
		return book.getId();
	}
	
	public String getTitle() {
		return book.getTitle();
	}
	
}
