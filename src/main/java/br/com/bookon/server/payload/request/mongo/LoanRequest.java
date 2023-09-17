package br.com.bookon.server.payload.request.mongo;

public class LoanRequest {

	private Integer borrowerId;

    private Integer lenderId;

    private Long bookId;

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Integer getLenderId() {
		return lenderId;
	}

	public void setLenderId(Integer lenderId) {
		this.lenderId = lenderId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

}
