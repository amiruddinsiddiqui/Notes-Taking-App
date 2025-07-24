package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.service.NoteEntryService;
import com.amir.notestakingapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/note")
public class NoteController {
    //controller ---> service ---> repository

//    @Autowired
//    private NoteEntry noteEntry;
    @Autowired
    private NoteEntryService noteEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-note")
    public ResponseEntity<List<NoteEntry>> getNoteOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<NoteEntry> allNotes = user.getNoteEntryList();
        if ((allNotes != null) && !allNotes.isEmpty()){
            return new ResponseEntity<>(allNotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postnote")
    public ResponseEntity<?> createEntries(@RequestBody NoteEntry noteEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
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
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            if (user != null){
                List<NoteEntry> collect = user.getNoteEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
                if (!collect.isEmpty()){
                    Optional<NoteEntry> note = noteEntryService.getNoteById(id);
                    if (note.isPresent()){
                        return new ResponseEntity<>(note.get(), HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }


    @DeleteMapping("/delbyid/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            List<NoteEntry> collect = user.getNoteEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            if (!collect.isEmpty()){
                Optional<NoteEntry> note = noteEntryService.getNoteById(id);
                if (note.isPresent()){
                    boolean removed = noteEntryService.deleteById(id, userName);
                    if (removed){
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NoteEntry> updateEntryById(
            @PathVariable ObjectId id,
            @RequestBody NoteEntry updateEntry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        List<NoteEntry> collect = user.getNoteEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<NoteEntry> note = noteEntryService.getNoteById(id);
            if (note.isPresent()){
                NoteEntry old = note.get();
                old.setTitle(!(updateEntry.getTitle() == null && updateEntry.getTitle().isEmpty()) ? updateEntry.getTitle() : old.getTitle());
                old.setContent(!(updateEntry.getContent() == null && updateEntry.getContent().isEmpty()) ? updateEntry.getContent() : old.getContent());
                noteEntryService.saveNote(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
