package br.com.bookon.server.payload.response.mongo;

import br.com.bookon.server.models.mongo.Book;

public class BookResponse {

	private Book book;

	public Long getId() {
		return book.getId();
	}
	
	public String getTitle() {
		return book.getTitle();
	}

	public BookResponse(Book book) {
		this.book = book;
	}
	
}
