package com.amir.notestakingapp.service;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.repository.NoteEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NoteEntryService {

    @Autowired
    private NoteEntryRepository noteEntryRepository;

    public List<NoteEntry> getAllNote(){
        return noteEntryRepository.findAll();
    }

    public void saveNote(NoteEntry noteEntry){
        noteEntryRepository.save(noteEntry);
    }

    public Optional<NoteEntry> getNoteById(ObjectId id){
        return noteEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        noteEntryRepository.deleteById(id);
    }
}
