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
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    String addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String login) {
        User user = new User(firstName, lastName, login);
        PhoneBook phoneBook = new PhoneBook(user);

        userRepository.save(user);
        phoneBookRepository.save(phoneBook);
        return "Saved";
    }

    @GetMapping(path = "/get_user")
    public @ResponseBody
    User getUser(@RequestParam String id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("User with id " + id + " is not found.");
        }
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

    @GetMapping(path = "/get_all_users")
    public @ResponseBody
    Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
}
