package kz.spring.boot.librarytz1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = "full_name"))
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long personId;

    @NotBlank(message = "Полное имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Полное имя должно быть от 2 до 100 символов")
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @Min(value = 1900, message = "Год рождения должен быть не ранее 1900")
    private Integer birthYear;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Person(Long personId, String fullName, Integer birthYear) {
        this.personId = personId;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
}
