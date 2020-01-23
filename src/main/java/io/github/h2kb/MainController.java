package io.github.h2kb;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.entity.Number;
import io.github.h2kb.entity.PhoneBook;
import io.github.h2kb.entity.User;
import io.github.h2kb.entity.enums.EntryType;
import io.github.h2kb.entity.enums.NumberType;
import io.github.h2kb.repository.EntryRepository;
import io.github.h2kb.repository.NumberRepository;
import io.github.h2kb.repository.PhoneBookRepository;
import io.github.h2kb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/phone_book")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private NumberRepository numberRepository;

    @PostMapping(path = "/add_user")
    public @ResponseBody
    ResponseEntity<User> addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String login) {
        User user = new User(firstName, lastName, login);
        PhoneBook phoneBook = new PhoneBook(user);

        userRepository.save(user);
        phoneBookRepository.save(phoneBook);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/get_user")
    public @ResponseBody
    ResponseEntity<User> getUser(@RequestParam String id) {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(path = "update_user")
    public @ResponseBody
    ResponseEntity<User> updateUser(@RequestParam String id, @RequestParam String firstName, @RequestParam String lastName) {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete_user")
    public @ResponseBody
    ResponseEntity<User> deleteUser(@RequestParam String id) {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);
        optionalPhoneBook.ifPresent(phoneBook -> phoneBookRepository.delete(phoneBook));

        userRepository.delete(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/add_number")
    public @ResponseBody
    String addNewNumber(@RequestParam String ownerId, @RequestParam EntryType entryType, @RequestParam String phoneNumber,
                        @RequestParam String name, @RequestParam(defaultValue = "NULL") NumberType numberType) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(ownerId));
        User user = optionalUser.orElseGet(User::new);
        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);
        PhoneBook phoneBook = optionalPhoneBook.orElseGet(PhoneBook::new);
        Entry entry = new Entry(phoneBook, name, entryType);
        Number number = new Number(entry, phoneNumber, numberType);

        entryRepository.save(entry);
        numberRepository.save(number);
        return "Saved";
    }

    @GetMapping(path = "/get_number")
    public @ResponseBody
    ResponseEntity<Entry> getNumber(@RequestParam String id) {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(id));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }

    @PostMapping(path = "/update_number")
    public @ResponseBody
    ResponseEntity<Entry> updateNumber(@RequestParam String id, String entryName) {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(id));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
        }

        if (entryName.isEmpty()) {
            return new ResponseEntity<Entry>(entry, HttpStatus.OK);
        } else {
            entry.setName(entryName);
        }

        entryRepository.save(entry);
        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete_number")
    public @ResponseBody
    ResponseEntity<Entry> deleteNumber(@RequestParam String id) {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(id));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
        }

        Optional<Number> optionalNumber = numberRepository.findByEntry(entry);
        optionalNumber.ifPresent(number -> numberRepository.delete(number));

        entryRepository.delete(entry);
        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }

    @GetMapping(path = "/get_all_users")
    public @ResponseBody
    Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
}
