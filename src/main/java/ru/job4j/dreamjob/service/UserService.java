package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.UserDBStore;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class UserService {

    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public Collection<User> findAll() {
        return store.findAll();
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return store.findByEmailAndPwd(email, password);
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public void create(User user) {
        store.create(user);
    }

    public void update(User user) {
        store.update(user);
    }
}