package com.amir.notestakingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "userlogin")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef
    List<NoteEntry> noteEntryList = new ArrayList<>();
}
