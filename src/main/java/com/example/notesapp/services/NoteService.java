package com.example.notesapp.services;

import com.example.notesapp.models.NoteDTO;
import com.example.notesapp.models.Notes;
import com.example.notesapp.models.Users;
import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Notes> getAllNotes() {
        return noteRepository.findAll();
    }

    public Notes getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Notes createNote(NoteDTO noteDTO) {
        Users user = userRepository.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notes note = new Notes();
        note.setUser(user);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setIsFavorite(noteDTO.getIsFavorite());

        return noteRepository.save(note);
    }

    public Notes updateNote(NoteDTO noteDTO) {
        Notes note = noteRepository.findById(noteDTO.getNoteId())
                .orElseThrow(() -> new RuntimeException("Note not found"));

        Users user = userRepository.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setIsFavorite(noteDTO.getIsFavorite());

        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }


    public List<Notes> getNotesByUserIdentifier(String userIdentifier) {
        // Find users by userIdentifier (returns a list of users)
        List<Users> users = userRepository.findByUserIdentifier(userIdentifier);

        if (!users.isEmpty()) {
            // Return all notes for all matching users
            return noteRepository.findByUserIn(users);  // findByUserIn for a list of users
        } else {
            // Handle case where no user is found (optional)
            return null;
        }
    }
}
