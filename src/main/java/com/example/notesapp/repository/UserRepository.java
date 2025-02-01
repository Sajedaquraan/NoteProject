package com.example.notesapp.repository;

import com.example.notesapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// todo annotated with @Repository
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByUserIdentifier(String userIdentifier);
}
