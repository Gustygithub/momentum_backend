package com.momentum.moods.dto;

import java.time.LocalDate;

public class MoodEntryRequest {

    private String userId;
    private String emotion;
    private String note;
    private LocalDate date;

    public MoodEntryRequest() {}

    // Getters y setters

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmotion() { return emotion; }
    public void setEmotion(String emotion) { this.emotion = emotion; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
