package com.edigest.journalApp.service;



import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.repository.JournalEtryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEtryRepository journalEtryRepository;


    public void saveEntry(JournalEntry journalEntry){
        journalEtryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEtryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEtryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEtryRepository.deleteById(id);
    }

}
