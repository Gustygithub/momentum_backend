package com.momentum.moods.controller;

import com.momentum.moods.dto.MoodEntryRequest;
import com.momentum.moods.model.MoodEntry;
import com.momentum.moods.repository.MoodEntryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/moods")
@CrossOrigin(origins = "*")
public class MoodEntryController {

    private final MoodEntryRepository repository;

    public MoodEntryController(MoodEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<MoodEntry> createMood(@RequestBody MoodEntryRequest request) {
        LocalDate date = request.getDate() != null ? request.getDate() : LocalDate.now();

        MoodEntry entry = new MoodEntry(
                request.getUserId(),
                request.getEmotion(),
                request.getNote(),
                date
        );

        MoodEntry saved = repository.save(entry);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<MoodEntry>> getMoods(@RequestParam("userId") String userId) {
        List<MoodEntry> moods = repository.findByUserIdOrderByDateDesc(userId);
        return ResponseEntity.ok(moods);
    }
}
