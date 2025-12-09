package com.momentum.moods.controller;

import com.momentum.moods.dto.DiaryEntryRequest;
import com.momentum.moods.model.DiaryEntry;
import com.momentum.moods.repository.DiaryEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diary")
@CrossOrigin(origins = "*")
public class DiaryEntryController {

    private final DiaryEntryRepository repository;

    public DiaryEntryController(DiaryEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> createDiaryEntry(@RequestBody DiaryEntryRequest request) {
        // Validations
        if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "userId es requerido");
            return ResponseEntity.badRequest().body(error);
        }
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "title es requerido");
            return ResponseEntity.badRequest().body(error);
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "content es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        try {
            String date = request.getDate() != null ? request.getDate() : LocalDate.now().toString();

            DiaryEntry entry = new DiaryEntry(
                    request.getUserId(),
                    request.getTitle(),
                    request.getContent(),
                    date
            );

            DiaryEntry saved = repository.save(entry);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear entrada: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getDiaryEntries(@RequestParam("userId") String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "userId es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        try {
            List<DiaryEntry> entries = repository.findByUserIdOrderByDateDesc(userId);
            return ResponseEntity.ok(entries);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener entradas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiaryEntry(
            @PathVariable String id,
            @RequestBody DiaryEntryRequest request) {
        
        if (id == null || id.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "id es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "title es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "content es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        try {
            java.util.Optional<DiaryEntry> optionalEntry = repository.findById(id);
            
            if (optionalEntry.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Entrada no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            DiaryEntry entry = optionalEntry.get();
            entry.setTitle(request.getTitle());
            entry.setContent(request.getContent());

            DiaryEntry updated = repository.save(entry);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar entrada: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiaryEntry(@PathVariable String id) {
        
        if (id == null || id.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "id es requerido");
            return ResponseEntity.badRequest().body(error);
        }

        try {
            java.util.Optional<DiaryEntry> optionalEntry = repository.findById(id);
            
            if (optionalEntry.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Entrada no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            repository.deleteById(id);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Entrada eliminada exitosamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar entrada: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
