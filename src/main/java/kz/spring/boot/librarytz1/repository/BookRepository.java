package kz.spring.boot.librarytz1.repository;

import kz.spring.boot.librarytz1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPersonPersonId(Long personId);
}
