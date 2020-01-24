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
import io.github.h2kb.service.EntryService;
import io.github.h2kb.service.impl.NumberServiceImpl;
import io.github.h2kb.service.impl.UserServiceImpl;
import javassist.NotFoundException;
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
    private UserServiceImpl userService;

    @Autowired
    private NumberServiceImpl numberService;

    @Autowired
    private EntryService entryService;
//
//    @Autowired
//    private NumberRepository numberRepository;

    @PostMapping(path = "/add_user")
    public @ResponseBody
    ResponseEntity<User> addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String login) {
        User user = userService.addUser(firstName, lastName, login);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/get_user")
    public @ResponseBody
    ResponseEntity<User> getUser(@RequestParam String userId) throws NotFoundException {

        return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
    }

    @PutMapping(path = "/edit_user_first_name")
    public @ResponseBody
    ResponseEntity<User> editUserFirstName(@RequestParam String userId, @RequestParam String firstName) throws NotFoundException {

        return new ResponseEntity<User>(userService.editUserFirstName(userId, firstName), HttpStatus.OK);
    }

    @PutMapping(path = "/edit_user_last_name")
    public @ResponseBody
    ResponseEntity<User> editUserLastName(@RequestParam String userId, @RequestParam String lastName) throws NotFoundException {

        return new ResponseEntity<User>(userService.editUserLastName(userId, lastName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete_user")
    public @ResponseBody
    ResponseEntity<User> deleteUser(@RequestParam String userId) throws NotFoundException {

        return new ResponseEntity<User>(userService.deleteUser(userId), HttpStatus.OK);
    }
//
//    @PostMapping(path = "/add_entry")
//    public @ResponseBody
//    ResponseEntity<Entry> addEntry(@RequestParam String ownerId, @RequestParam EntryType entryType, @RequestParam String phoneNumber,
//                                   @RequestParam String name, @RequestParam(defaultValue = "NULL") NumberType numberType) {
//        User user;
//        PhoneBook phoneBook = null;
//        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(ownerId));
//        if (optionalUser.isPresent()) {
//            user = optionalUser.get();
//        } else {
//            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
//        }
//
//        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);
//
//        if (optionalPhoneBook.isPresent()) {
//            phoneBook = optionalPhoneBook.get();
//        }
//        Entry entry = new Entry(phoneBook, name, entryType);
//        Number number = new Number(entry, phoneNumber, numberType);
//
//        entryRepository.save(entry);
//        numberRepository.save(number);
//        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
//    }
//
    @GetMapping(path = "/get_entry")
    public @ResponseBody
    ResponseEntity<Entry> getEntry(@RequestParam String entryId) throws NotFoundException {

        return new ResponseEntity<Entry>(entryService.getEntry(entryId), HttpStatus.OK);
    }
//
//    @PostMapping(path = "/update_entry")
//    public @ResponseBody
//    ResponseEntity<Entry> updateEntry(@RequestParam String id, String entryName) {
//        Entry entry;
//        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(id));
//
//        if (optionalEntry.isPresent()) {
//            entry = optionalEntry.get();
//        } else {
//            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
//        }
//
//        if (entryName.isEmpty()) {
//            return new ResponseEntity<Entry>(entry, HttpStatus.OK);
//        } else {
//            entry.setName(entryName);
//        }
//
//        entryRepository.save(entry);
//        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/delete_entry")
//    public @ResponseBody
//    ResponseEntity<Entry> deleteEntry(@RequestParam String id) {
//        Entry entry;
//        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(id));
//
//        if (optionalEntry.isPresent()) {
//            entry = optionalEntry.get();
//        } else {
//            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
//        }
//
//        Optional<Number> optionalNumber = numberRepository.findByEntry(entry);
//        optionalNumber.ifPresent(number -> numberRepository.delete(number));
//
//        entryRepository.delete(entry);
//        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
//    }
//
    @PostMapping(path = "/add_number")
    public @ResponseBody
    ResponseEntity<Number> addNumber(@RequestParam String entryId, @RequestParam NumberType numberType, @RequestParam String phoneNumber) throws NotFoundException {

        return new ResponseEntity<Number>(numberService.addNumber(entryId, numberType, phoneNumber), HttpStatus.OK);
    }

    @PutMapping(path = "/edit_phone_number")
    public @ResponseBody
    ResponseEntity<Number> editPhoneNumber(@RequestParam String numberId, @RequestParam String phoneNumber) throws NotFoundException {

        return new ResponseEntity<Number>(numberService.editPhoneNumber(numberId, phoneNumber), HttpStatus.OK);
    }
//
//    @GetMapping(path = "/get_user_phone_book")
//    public @ResponseBody
//    ResponseEntity<PhoneBook> getUserPhoneBook(@RequestParam String ownerId) {
//        User user;
//        PhoneBook phoneBook = null;
//        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(ownerId));
//
//        if (optionalUser.isPresent()) {
//            user = optionalUser.get();
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);
//
//        if (optionalPhoneBook.isPresent()) {
//            phoneBook = optionalPhoneBook.get();
//        }
//
//        return new ResponseEntity<PhoneBook>(phoneBook, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/get_all_users")
//    public @ResponseBody
//    Iterable<User> getAllUser() {
//        return userRepository.findAll();
//    }
}
