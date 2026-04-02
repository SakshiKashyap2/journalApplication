package com.exampleJournalApplication.repository;


import com.exampleJournalApplication.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface JournalEntryRepository  extends MongoRepository<JournalEntry, ObjectId> {


}
