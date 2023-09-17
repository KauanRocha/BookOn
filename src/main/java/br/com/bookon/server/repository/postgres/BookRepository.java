package br.com.bookon.server.repository.postgres;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.models.postgres.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book>, ListCrudRepository<Book, Long> {

	@Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findBookById(Long id);
}
