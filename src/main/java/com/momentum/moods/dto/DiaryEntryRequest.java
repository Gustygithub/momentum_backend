package com.momentum.moods.dto;

import java.time.LocalDate;

public class DiaryEntryRequest {

    private String userId;
    private String title;
    private String content;
    private LocalDate date;

    public DiaryEntryRequest() {}

    // Getters y setters

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
