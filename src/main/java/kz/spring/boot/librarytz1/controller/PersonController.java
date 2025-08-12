package kz.spring.boot.librarytz1.controller;

import kz.spring.boot.librarytz1.entity.Person;
import kz.spring.boot.librarytz1.service.BookService;
import kz.spring.boot.librarytz1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonService personService;
    private final BookService bookService;

    public PersonController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping("/person")
    public String getAllPerson(Model model) {
        model.addAttribute("persons", personService.getAllPerson());
        return "person-list";
    }

    @GetMapping("/person/new")
    public String showCreatePersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-create";
    }

    @PostMapping("/person")
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "person-create";
        }
        try {
            personService.savePerson(person);
            return "redirect:/person";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "person-create";
        }
    }

    @GetMapping("/person/{id}")
    public String getPerson(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        model.addAttribute("books", bookService.getBooksByPersonId(id));
        return "person-detail";
    }

    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/person";
    }

    @GetMapping("/person/{id}/edit")
    public String showEditPersonForm(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person-edit";
    }

    @PutMapping("/person/{id}")
    public String editPerson(@PathVariable Long id, @Valid @ModelAttribute Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "person-edit";
        }
        try {
            personService.updatePerson(id, person);
            return "redirect:/person/" + id;
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "person-edit";
        }
    }
}