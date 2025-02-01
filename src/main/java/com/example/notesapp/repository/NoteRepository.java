package com.example.notesapp.repository;

import com.example.notesapp.models.Notes;
import com.example.notesapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUserIn(List<Users> users);

}
