package br.com.bookon.server.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.models.mongo.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String>{

}
