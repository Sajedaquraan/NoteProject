package com.example.notesapp.services;

import com.example.notesapp.models.NoteDTO;
import com.example.notesapp.models.Notes;
import com.example.notesapp.models.Users;
import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    private NoteDTO convertToDTO(Notes note) {
        NoteDTO dto = new NoteDTO();
        dto.setNoteId(note.getNoteId());
        dto.setUserId(note.getUser().getUserId());
        dto.setTitle(note.getTitle());
        dto.setContent(note.getContent());
        dto.setIsFavorite(note.getIsFavorite());
        return dto;
    }

    private Notes convertToEntity(NoteDTO dto, Users user) {
        Notes note = new Notes();
        note.setNoteId(dto.getNoteId());
        note.setUser(user);
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setIsFavorite(dto.getIsFavorite());
        return note;
    }

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id) {
        return noteRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public NoteDTO createNote(NoteDTO noteDTO) {
        Users user = userRepository.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notes note = convertToEntity(noteDTO, user);
        Notes savedNote = noteRepository.save(note);
        return convertToDTO(savedNote);
    }

    public NoteDTO updateNote(NoteDTO noteDTO) {
        Notes existingNote = noteRepository.findById(noteDTO.getNoteId())
                .orElseThrow(() -> new RuntimeException("Note not found"));

        Users user = userRepository.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingNote.setUser(user);
        existingNote.setTitle(noteDTO.getTitle());
        existingNote.setContent(noteDTO.getContent());
        existingNote.setIsFavorite(noteDTO.getIsFavorite());

        Notes updatedNote = noteRepository.save(existingNote);
        return convertToDTO(updatedNote);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<NoteDTO> getNotesByUserIdentifier(String userIdentifier) {
        List<Users> users = userRepository.findByUserIdentifier(userIdentifier);

        if (!users.isEmpty()) {
            return noteRepository.findByUserIn(users)
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
