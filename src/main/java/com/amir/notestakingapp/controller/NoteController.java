package com.amir.notestakingapp.controller;

import com.amir.notestakingapp.entity.NoteEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/note")
public class NoteController {

    Map<String, NoteEntry> noteEntryMap = new HashMap<>();

    @GetMapping("/get")
    public List<NoteEntry> getAll(){
        return new ArrayList<>(noteEntryMap.values());
    }

    @PostMapping("/post")
    public NoteEntry createEntries(@RequestBody NoteEntry noteEntry){
        noteEntryMap.put(noteEntry.getId(), noteEntry);
        return noteEntry;
    }

    @GetMapping("/getbyid/{myid}")
    public NoteEntry getbyid(@PathVariable String myid){
        return  noteEntryMap.get(myid);
    }

    @DeleteMapping("/delbyid/{myId}")
    public boolean deleteById(@PathVariable String myId){
        noteEntryMap.remove(myId);
        return true;
    }

    @PutMapping("/update/{id}")
    public NoteEntry updateEntryById(@PathVariable String id, @RequestBody NoteEntry myUpdatedEntry){
        noteEntryMap.put(myUpdatedEntry.getId(), myUpdatedEntry);
        return myUpdatedEntry;
    }
}
