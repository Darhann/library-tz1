package kz.spring.boot.librarytz1.service;

import kz.spring.boot.librarytz1.entity.Book;
import kz.spring.boot.librarytz1.entity.Person;
import kz.spring.boot.librarytz1.repository.BookRepository;
import kz.spring.boot.librarytz1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void createBook(Book book) {
        if (book.getBookId() != null) {
            throw new IllegalArgumentException("BookId must be empty");
        }
        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found by ID=%s".formatted(id)));
    }

    public void deleteBook(Long id) {
        if (bookRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Book not found by ID=%s".formatted(id));
        }
        bookRepository.deleteById(id);
    }

    public void updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found by ID=%s".formatted(id)));

        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setYear(updatedBook.getYear());

        bookRepository.save(existingBook);
    }

    public void assignBookToPerson(Long bookId, Long personId) {
        Book book = getBookById(bookId);
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found by ID=%s".formatted(personId)));
        book.setPerson(person);
        bookRepository.save(book);
    }

    public void releaseBook(Long bookId) {
        Book book = getBookById(bookId);
        book.setPerson(null);
        bookRepository.save(book);
    }

    public List<Book> getBooksByPersonId(Long personId) {
        return bookRepository.findByPersonPersonId(personId);
    }

}
