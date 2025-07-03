package com.amir.notestakingapp.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "note_entries")
public class NoteEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public LocalDateTime getLocalDateTime() {
        return createdAt;
    }

    public void setLocalDateTime(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
