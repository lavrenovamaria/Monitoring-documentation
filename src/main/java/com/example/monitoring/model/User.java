package com.example.monitoring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telegramId;

    @ElementCollection
    private List<String> interests;

    public List<String> getInterests() {
        return null;
    }

    // Getters and setters
}
