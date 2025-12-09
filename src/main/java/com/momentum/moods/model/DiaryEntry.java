package com.momentum.moods.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diary_entries")
public class DiaryEntry {

    @Id
    private String id;

    private String userId;
    private String title;
    private String content;
    private String date;  // YYYY-MM-DD format

    public DiaryEntry() {}

    public DiaryEntry(String userId, String title, String content, String date) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    // Getters y setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
