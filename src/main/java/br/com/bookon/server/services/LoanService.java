package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookon.server.models.mongo.Loan;
import br.com.bookon.server.models.mongo.Book;
import br.com.bookon.server.models.mongo.User;
import br.com.bookon.server.payload.request.mongo.LoanRequest;
import br.com.bookon.server.repository.mongo.LoanRepository;
import br.com.bookon.server.repository.postgres.BookRepository;
import br.com.bookon.server.repository.postgres.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(String id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.orElse(null);
    }

    public Loan createLoan(LoanRequest loanRequest) {
    	Loan loan = new Loan();
        Optional<User> borrower = userRepository.findById(loanRequest.getBorrowerId());
        if(borrower.isPresent()) {
        	loan.setBorrowerUser(borrower);
        }
        
        Optional<User> lender = userRepository.findById(loanRequest.getLenderId());
        if(lender.isPresent()) {
        	loan.setLenderUser(lender.get());
        }
        
        Optional<Book> book = bookRepository.findById(loanRequest.getBookId());
        if(book.isPresent()) {
        	loan.setBook(book.get());
        }
        
        loan.setReturnDate(null);
        
        return loanRepository.save(loan);
    }


    public boolean deleteLoan(String id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true; 
        } else {
            return false;
        }
    }
}
