package kz.spring.boot.librarytz1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long bookId;

    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 1, max = 200, message = "Название должно быть от 1 до 200 символов")
    private String name;

    @NotBlank(message = "Автор не может быть пустым")
    @Size(min = 2, max = 100, message = "Автор должен быть от 2 до 100 символов")
    private String author;

    @Min(value = 1500, message = "Год издания должен быть не ранее 1500")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Book(Long bookId, String name, String author, Integer year) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long book_id) {
        this.bookId = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
