package io.github.h2kb.service.impl;

import io.github.h2kb.entity.PhoneBook;
import io.github.h2kb.entity.User;
import io.github.h2kb.repository.PhoneBookRepository;
import io.github.h2kb.repository.UserRepository;
import io.github.h2kb.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Override
    public User addUser(String firstName, String lastName, String login) {
        User user = new User(firstName, lastName, login);
        PhoneBook phoneBook = new PhoneBook(user);

        userRepository.save(user);
        phoneBookRepository.save(phoneBook);
        return user;
    }

    @Override
    public User getUser(String userId) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }
    }

    @Override
    public User editUserFirstName(String userId, String firstName) throws NotFoundException {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        user.setFirstName(firstName);
        userRepository.save(user);
        return user;
    }

    @Override
    public User editUserLastName(String userId, String lastName) throws NotFoundException {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        user.setLastName(lastName);
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUser(String userId) throws NotFoundException {
        User user;
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);
        optionalPhoneBook.ifPresent(phoneBook -> phoneBookRepository.delete(phoneBook));

        userRepository.delete(user);
        return user;
    }
}
