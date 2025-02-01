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

    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping("/create")
    public NoteDTO createNote(@RequestBody NoteDTO noteDTO) {
        return noteService.createNote(noteDTO);
    }

    @PostMapping("/update")
    public NoteDTO updateNote(@RequestBody NoteDTO noteDTO) {
        return noteService.updateNote(noteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }

    @GetMapping("/user/{userIdentifier}")
    public List<NoteDTO> getNotesByUserIdentifier(@PathVariable String userIdentifier) {
        return noteService.getNotesByUserIdentifier(userIdentifier);
    }
}
