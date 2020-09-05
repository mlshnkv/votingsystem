package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.AuthorizedUser;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.moloshnikov.votingsystem.to.UserTo;
import org.moloshnikov.votingsystem.util.UserUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.*;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public User create(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        return create(UserUtil.createNewFromTo(userTo));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user, int id) {
        Assert.notNull(user, "user must not be null");
        assureIdConsistent(user, id);
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void update(UserTo userTo, int userId) {
        User user = get(userTo.id());
        assureIdConsistent(userTo, userId);
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        repository.save(updatedUser);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
