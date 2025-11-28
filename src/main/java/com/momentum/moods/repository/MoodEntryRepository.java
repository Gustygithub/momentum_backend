package com.momentum.moods.repository;

import com.momentum.moods.model.MoodEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MoodEntryRepository extends MongoRepository<MoodEntry, String> {

    List<MoodEntry> findByUserIdOrderByDateDesc(String userId);
}
