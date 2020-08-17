package org.moloshnikov.votingsystem.repository.user;

import org.moloshnikov.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
