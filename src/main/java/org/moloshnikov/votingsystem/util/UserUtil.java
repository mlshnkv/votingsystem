package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.Role;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.to.UserTo;

public class UserUtil {

    private  UserUtil() {
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}