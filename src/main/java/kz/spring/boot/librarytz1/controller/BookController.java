package kz.spring.boot.librarytz1.controller;

import kz.spring.boot.librarytz1.entity.Book;
import kz.spring.boot.librarytz1.service.BookService;
import kz.spring.boot.librarytz1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping("/book")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list";
    }

    @GetMapping("/book/new")
    public String showCreateBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-create";
    }

    @PostMapping("/book")
    public String createBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-create";
        }
        bookService.createBook(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("persons", personService.getAllPerson()); // Для select
        return "book-detail";
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/edit")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-edit";
    }

    @PutMapping("/book/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "book-edit";
        }
        try {
            bookService.updateBook(id, book);
            return "redirect:/book/" + id;
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "book-edit";
        }
    }

    @PostMapping("/book/{id}/assign")
    public String assignBook(@PathVariable Long id, @RequestParam Long personId) {
        bookService.assignBookToPerson(id, personId);
        return "redirect:/book/" + id;
    }

    @PostMapping("/book/{id}/release")
    public String releaseBook(@PathVariable Long id) {
        bookService.releaseBook(id);
        return "redirect:/book/" + id;
    }
}