package kz.spring.boot.librarytz1.config;


import kz.spring.boot.librarytz1.entity.Person;
import kz.spring.boot.librarytz1.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
//public class PersonConfig {
//
//    @Bean
//    CommandLineRunner personDataLoader(PersonRepository personRepository) {
//        return (args) -> {
//            var personList = List.of(
//                new Person(null, "Jake Huge", 1993),
//                new Person(null, "Mike Groove", 2003)
//        );
//            personRepository.saveAll(personList);
//        };
//    }
//}
