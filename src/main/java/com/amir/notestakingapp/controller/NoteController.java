package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.service.NoteEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<NoteEntry> getAll(){
        if ((noteEntryService.getAllNote() != null) && !noteEntryService.getAllNote().isEmpty()){
            return noteEntryService.getAllNote();
        }
        return new ArrayList<>();
    }

    @PostMapping("/postnote")
    public NoteEntry createEntries(@RequestBody NoteEntry noteEntry){
        noteEntry.setLocalDateTime(LocalDateTime.now());
        noteEntryService.saveNote(noteEntry);
        return noteEntry;
    }

    @GetMapping("/getbyid/{id}")
    public NoteEntry getbyid(@PathVariable ObjectId id){
        return noteEntryService.getNoteById(id).orElse(null);
    }


    @DeleteMapping("/delbyid/{id}")
    public boolean deleteById(@PathVariable ObjectId id){
        if(id != null && !id.equals("")){
            noteEntryService.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/{id}")
    public NoteEntry updateEntryById(@PathVariable ObjectId id, @RequestBody NoteEntry updateEntry){
        NoteEntry isNote = noteEntryService.getNoteById(id).orElse(null);
        if (isNote != null){
            isNote.setTitle(!(updateEntry.getTitle() == null) && !updateEntry.getTitle().isEmpty() ? updateEntry.getTitle() : isNote.getTitle());
            isNote.setContent(!(updateEntry.getContent() == null) && !updateEntry.getContent().isEmpty() ? updateEntry.getContent() : isNote.getContent());
        }
        noteEntryService.saveNote(isNote);
        return isNote;
    }
}
