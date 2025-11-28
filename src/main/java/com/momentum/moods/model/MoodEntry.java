package com.momentum.moods.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "mood_entries")
public class MoodEntry {

    @Id
    private String id;

    private String userId;
    private String emotion;
    private String note;
    private LocalDate date;

    public MoodEntry() {}

    public MoodEntry(String userId, String emotion, String note, LocalDate date) {
        this.userId = userId;
        this.emotion = emotion;
        this.note = note;
        this.date = date;
    }

    // Getters y setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmotion() { return emotion; }
    public void setEmotion(String emotion) { this.emotion = emotion; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
