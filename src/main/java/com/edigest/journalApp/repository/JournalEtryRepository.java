package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEtryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
