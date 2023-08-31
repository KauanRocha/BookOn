package br.com.bookon.server.payload.response;

import br.com.bookon.server.models.Book;

public class BookResponse {
	  private Book book;

	    public BookResponse(Book book) {
	        this.book = book;
	    }

	    public String getBookTitle() {
	        return book.getTitle();
	    }
	    
	    public String getBookAUthor() {
	        return book.getAuthor();
	    }

	    public String getBookCathegory() {
	        return book.getCategory();
	    }

	    public Integer getUserId() {
	        return book.getUser().getId();
	    }
}
