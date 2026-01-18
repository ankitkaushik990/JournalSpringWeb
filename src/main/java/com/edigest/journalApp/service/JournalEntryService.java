package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEtryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry",e);
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

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed= user.getJournalEntries().removeIf(x-> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEtryRepository.deleteById(id);
            }
        }catch( Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occured while deleting the Entry",e);
        }
        return removed;

        }

//    public List<JournalEntry> findByUserName(String userName){
//
//    }

}
