package com.amir.notestakingapp.service;

import com.amir.notestakingapp.entity.NoteEntry;
import com.amir.notestakingapp.entity.User;
import com.amir.notestakingapp.repository.NoteEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class NoteEntryService {

    @Autowired
    private NoteEntryRepository noteEntryRepository;
    @Autowired
    private UserService userService;

    public List<NoteEntry> getAllNote(){
        return noteEntryRepository.findAll();
    }

    @Transactional
    public void saveNote(NoteEntry noteEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            noteEntry.setCreatedAt(LocalDateTime.now());
            NoteEntry savedNote = noteEntryRepository.save(noteEntry);
            user.getNoteEntryList().add(savedNote);
            userService.save(user);
        } catch (Exception e) {
//            log.error("Exception", e);
            throw new RuntimeException("Exception", e);
        }
    }

    @Transactional
    public void saveNote(NoteEntry noteEntry){
        try {
            noteEntryRepository.save(noteEntry);
        } catch (Exception e) {
            throw new RuntimeException("Exception", e);
        }
    }

    public Optional<NoteEntry> getNoteById(ObjectId id){
        return noteEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getNoteEntryList().removeIf(x -> x.getId().equals(id));
        userService.save(user);
        noteEntryRepository.deleteById(id);
    }
}
