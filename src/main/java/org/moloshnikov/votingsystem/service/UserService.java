package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.AuthorizedUser;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFound;
import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    User create(User user){
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    User get(int id){
        return checkNotFoundWithId(repository.get(id), id);
    }

    User getByEmail(String email){
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    List<User> getAll(){
        return repository.getAll();
    }

    void update(User user){
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
