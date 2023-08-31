package br.com.bookon.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "loans")
public class Loan {
    @Id
    private UUID id;
    
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

    public Loan(User borrowerUser, User lenderUser, Book book, LocalDate startDate, LocalDate dueDate) {
        this.id = UUID.randomUUID();
        this.borrowerUser = borrowerUser;
        this.lenderUser = lenderUser;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returned = false;
        this.lateFee = 0.0;  
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
}
