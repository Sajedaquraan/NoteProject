package com.example.notesapp.controller;

import com.example.notesapp.models.NoteDTO;
import com.example.notesapp.models.Notes;
import com.example.notesapp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Notes> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Notes getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping("/create")
    public Notes createNote(@RequestBody NoteDTO noteDTO) {
        return noteService.createNote(noteDTO);
    }

    @PostMapping("/update")
    public Notes updateNote(@RequestBody NoteDTO noteDTO) {
        return noteService.updateNote(noteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }

    @GetMapping("/user/{userIdentifier}")
    public List<Notes> getNotesByUserIdentifier(@PathVariable String userIdentifier) {
        return noteService.getNotesByUserIdentifier(userIdentifier);
    }
}
