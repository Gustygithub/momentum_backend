package com.momentum.moods.repository;

import com.momentum.moods.model.DiaryEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiaryEntryRepository extends MongoRepository<DiaryEntry, String> {

    List<DiaryEntry> findByUserIdOrderByDateDesc(String userId);
}
