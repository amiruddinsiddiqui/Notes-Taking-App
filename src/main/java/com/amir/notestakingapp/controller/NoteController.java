package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.service.NoteEntryService;
import com.amir.notestakingapp.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/getnote/{userName}")
    public ResponseEntity<?> getNoteOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<NoteEntry> allNotes = user.getNoteEntryList();
        if ((allNotes != null) && !allNotes.isEmpty()){
            return new ResponseEntity<>(allNotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postnote/{userName}")
    public ResponseEntity<?> createEntries(@RequestBody NoteEntry noteEntry, @PathVariable String userName){
        try {
            User user = userService.findByUserName(userName);
            if (user != null){
                noteEntryService.saveNote(noteEntry, userName);
                return new ResponseEntity<>(noteEntry, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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


    @DeleteMapping("/delbyid/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String userName){
        Optional<NoteEntry> note = noteEntryService.getNoteById(id);
        if (note.isPresent()){
            noteEntryService.deleteById(id, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{userName}/{id}")
    public ResponseEntity<NoteEntry> updateEntryById(
            @PathVariable ObjectId id,
            @RequestBody NoteEntry updateEntry,
            @PathVariable String userName
    ){
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
