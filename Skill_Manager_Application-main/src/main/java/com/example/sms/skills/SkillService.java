package com.example.sms.skills;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository repo;

    public SkillService(SkillRepository repo) {
        this.repo = repo;
    }

    public List<Skill> getAll() {
        return repo.findAll();
    }

    @Transactional
    public Skill create(Skill s) {
        if (s.getName() == null || s.getName().isBlank()) {
            throw new IllegalArgumentException("Skill name is required");
        }
        if (repo.existsByNameIgnoreCase(s.getName().trim())) {
            throw new IllegalArgumentException("Skill already exists");
        }
        // Normalize and save
        Skill toSave = new Skill();
        toSave.setName(s.getName().trim());
        toSave.setCategory(s.getCategory());
        return repo.save(toSave);
    }

    @Transactional
    public Skill update(Long id, Skill s) {
        var existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found: " + id));
        if (s.getName() == null || s.getName().isBlank()) {
            throw new IllegalArgumentException("Skill name is required");
        }
        // If changing name, check uniqueness
        String newName = s.getName().trim();
        if (!existing.getName().equalsIgnoreCase(newName)
                && repo.existsByNameIgnoreCase(newName)) {
            throw new IllegalArgumentException("Another skill with same name already exists");
        }
        existing.setName(newName);
        existing.setCategory(s.getCategory());
        return repo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Skill not found: " + id);
        }
        repo.deleteById(id);
    }

    // (Optional) getter if you really need it elsewhere
    public SkillRepository getRepo() {
        return repo;
    }
}