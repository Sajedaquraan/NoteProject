package com.example.notesapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Trash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trashId;

    @OneToOne
    @JoinColumn(name = "note_id", nullable = false)
    private Notes note;

    @Column(nullable = false, updatable = false)
    private LocalDateTime deletedAt = LocalDateTime.now();
}
