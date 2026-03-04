package com.example.sms.skill;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) { this.service = service; }

    @GetMapping
    public List<Skill> getAll() { return service.getAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Skill create(@RequestBody Skill s) { return service.create(s); }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill s) { return service.update(id, s); }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

	public SkillService getService() {
		return service;
	}
    
    
}