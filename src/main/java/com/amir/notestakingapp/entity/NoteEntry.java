package com.amir.notestakingapp.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "note_entries")
@Data
public class NoteEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
