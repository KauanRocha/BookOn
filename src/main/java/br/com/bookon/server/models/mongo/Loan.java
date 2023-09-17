package br.com.bookon.server.models.mongo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "loans")
public class Loan {
    @Id
    private String id;
    
    @Field("borrowerUser")
    private User borrowerUser;

    @Field("lenderUser")
    private User lenderUser;

    @Field("book")
    private Book book;

    @Field("startDate")
    @CreatedDate
    private LocalDateTime startDate;

    @Field("returnDate")
    private LocalDateTime returnDate;

	public String getId() {
		return id;
	}

	public User getBorrowerUser() {
		return borrowerUser;
	}

	public void setBorrowerUser(User borrowerUser) {
		this.borrowerUser = borrowerUser;
	}

	public User getLenderUser() {
		return lenderUser;
	}

	public void setLenderUser(User lenderUser) {
		this.lenderUser = lenderUser;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Loan(User borrowerUser, User lenderUser, Book book) {
		this.borrowerUser = borrowerUser;
		this.lenderUser = lenderUser;
		this.book = book;
	}

	public Loan() {
	}
	
}
