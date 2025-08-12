package kz.spring.boot.librarytz1.config;

import kz.spring.boot.librarytz1.entity.Book;
import kz.spring.boot.librarytz1.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
//public class BookConfig {
//
//    @Bean
//    public CommandLineRunner bookDataLoader(BookRepository bookRepository) {
//        return (args) -> {
//            var bookList = List.of(
//                new Book(null,"Harry Potter", "Joanne Rowling", 1997),
//                new Book(null, "Iron Flame", "Rebecca Yarros", 2023)
//            );
//            bookRepository.saveAll(bookList);
//        };
//    }
//
//}
