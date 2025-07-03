package com.amir.notestakingapp.repository;

import com.amir.notestakingapp.entity.NoteEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteEntryRepository extends MongoRepository<NoteEntry, ObjectId> {

}
