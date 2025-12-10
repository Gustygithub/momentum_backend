package com.momentum.moods.controller;

import com.momentum.moods.dto.DiaryEntryRequest;
import com.momentum.moods.model.DiaryEntry;
import com.momentum.moods.repository.DiaryEntryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/diary")
@CrossOrigin(origins = "*")
public class DiaryEntryController {

    private final DiaryEntryRepository repository;

    public DiaryEntryController(DiaryEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<DiaryEntry> createDiaryEntry(@RequestBody DiaryEntryRequest request) {
        LocalDate date = request.getDate() != null ? request.getDate() : LocalDate.now();

        DiaryEntry entry = new DiaryEntry(
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                date
        );

        DiaryEntry saved = repository.save(entry);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<DiaryEntry>> getDiaryEntries(@RequestParam("userId") String userId) {
        List<DiaryEntry> entries = repository.findByUserIdOrderByDateDesc(userId);
        return ResponseEntity.ok(entries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaryEntry> updateDiaryEntry(@PathVariable String id, @RequestBody DiaryEntryRequest request) {
        return repository.findById(id)
                .map(existingEntry -> {
                    existingEntry.setUserId(request.getUserId());
                    existingEntry.setTitle(request.getTitle());
                    existingEntry.setContent(request.getContent());
                    if (request.getDate() != null) {
                        existingEntry.setDate(request.getDate());
                    }
                    DiaryEntry updated = repository.save(existingEntry);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaryEntry(@PathVariable String id) {
        return repository.findById(id)
                .map(entry -> {
                    repository.delete(entry);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
