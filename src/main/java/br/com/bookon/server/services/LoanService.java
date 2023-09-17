package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookon.server.models.mongo.Loan;
import br.com.bookon.server.repository.mongo.LoanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(String id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.orElse(null);
    }

    public Loan createLoan(Loan loan) {
    	
        Loan loan2 = new Loan();
        
        return loanRepository.save(loan2);
    }


    public boolean deleteLoan(String id) {
        // Verifique se o empréstimo com o ID especificado existe
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true; // Empréstimo excluído com sucesso
        } else {
            return false; // Empréstimo não encontrado
        }
    }
}
