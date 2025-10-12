package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEtryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEtryRepository journalEtryRepository;

    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEtryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public void saveEntry(JournalEntry journalEntry){
        journalEtryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEtryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEtryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userService.saveEntry(user);
        journalEtryRepository.deleteById(id);
    }

}
