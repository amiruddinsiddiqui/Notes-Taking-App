package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.service.NoteEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/note")
public class NoteController {
    //controller ---> service ---> repository

    @Autowired
    private NoteEntryService noteEntryService;

    @GetMapping("/getnote")
//    public ResponseEntity<?> getAll() ////wildcard
    //or
    public ResponseEntity<List<NoteEntry>> getAll(){
        List<NoteEntry> allNotes = noteEntryService.getAllNote();
        if ((allNotes != null) && !allNotes.isEmpty()){
            return new ResponseEntity<>(noteEntryService.getAllNote(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postnote")
    public ResponseEntity<NoteEntry> createEntries(@RequestBody NoteEntry noteEntry){
        try {
            noteEntry.setLocalDateTime(LocalDateTime.now());
            noteEntryService.saveNote(noteEntry);
            return new ResponseEntity<>(noteEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<NoteEntry> getbyid(@PathVariable ObjectId id){
        Optional<NoteEntry> note = noteEntryService.getNoteById(id);
        if (note.isPresent()){
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/delbyid/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Optional<NoteEntry> note = noteEntryService.getNoteById(id);
        if (note.isPresent()){
            noteEntryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NoteEntry> updateEntryById(@PathVariable ObjectId id, @RequestBody NoteEntry updateEntry){
        NoteEntry isNote = noteEntryService.getNoteById(id).orElse(null);
        if (isNote != null){
            isNote.setTitle(!(updateEntry.getTitle() == null) && !updateEntry.getTitle().isEmpty() ? updateEntry.getTitle() : isNote.getTitle());
            isNote.setContent(!(updateEntry.getContent() == null) && !updateEntry.getContent().isEmpty() ? updateEntry.getContent() : isNote.getContent());
            noteEntryService.saveNote(isNote);
            return new ResponseEntity<>(isNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
