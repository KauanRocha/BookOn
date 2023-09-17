package br.com.bookon.server.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.bookon.server.models.postgre.Book;
import br.com.bookon.server.models.postgre.User;

import java.time.LocalDate;

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
    private LocalDate startDate;

    @Field("dueDate")
    private LocalDate dueDate;

    @Field("returnDate")
    private LocalDate returnDate;

    @Field("returned")
    private boolean returned;

    @Field("lateFee")
    private double lateFee;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public Loan(String id, User borrowerUser, User lenderUser, Book book, LocalDate startDate, LocalDate dueDate,
			LocalDate returnDate, boolean returned, double lateFee) {
		super();
		this.id = id;
		this.borrowerUser = borrowerUser;
		this.lenderUser = lenderUser;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.returned = returned;
		this.lateFee = lateFee;
	}

	public Loan() {
	}
	
}
