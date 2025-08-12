package kz.spring.boot.librarytz1.repository;

import kz.spring.boot.librarytz1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFullName(String fullName);
}
