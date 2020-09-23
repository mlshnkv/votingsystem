package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);
}