package kz.spring.boot.librarytz1.service;

import kz.spring.boot.librarytz1.entity.Person;
import kz.spring.boot.librarytz1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person not found by ID=%s".formatted(id)));
    }

    public void deletePerson(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Person not found by ID=%s".formatted(id));
        }
        personRepository.deleteById(id);
    }

    public void savePerson(Person person) {
        String trimmedFullName = person.getFullName() != null ? person.getFullName().trim() : "";
        if (trimmedFullName.isEmpty()) {
            throw new RuntimeException("Полное имя не может быть пустым");
        }
        if (personRepository.findByFullName(trimmedFullName).isPresent()) {
            throw new RuntimeException("Человек с таким ФИО уже существует");
        }
        person.setFullName(trimmedFullName);
        personRepository.save(person);
    }

    public void updatePerson(Long id, Person updatedPerson) {
        String trimmedFullName = updatedPerson.getFullName() != null ? updatedPerson.getFullName().trim() : "";
        if (trimmedFullName.isEmpty()) {
            throw new RuntimeException("Полное имя не может быть пустым");
        }
        Optional<Person> existingByName = personRepository.findByFullName(trimmedFullName);
        if (existingByName.isPresent() && !existingByName.get().getPersonId().equals(id)) {
            throw new RuntimeException("Человек с таким ФИО уже существует");
        }

        Person existing = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Человек не найден"));
        existing.setFullName(trimmedFullName);
        existing.setBirthYear(updatedPerson.getBirthYear());
        personRepository.save(existing);
    }

}
