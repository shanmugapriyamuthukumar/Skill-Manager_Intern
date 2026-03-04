package com.example.sms.project;

import com.example.sms.project.dto.ProjectCreateRequest;
import com.example.sms.project.dto.ProjectRequiredSkillRequest;
import com.example.sms.project.dto.SkillGapResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) { this.service = service; }

    @GetMapping
    public List<Project> list() { return service.list(); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Project create(@RequestBody @Valid ProjectCreateRequest req) { return service.create(req); }

    @GetMapping("/{projectId}/required-skills")
    public List<ProjectRequiredSkill> requiredSkills(@PathVariable Long projectId) {
        return service.requiredSkills(projectId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/required-skills")
    public ProjectRequiredSkill addRequiredSkill(@RequestBody @Valid ProjectRequiredSkillRequest req) {
        return service.addRequiredSkill(req);
    }

    @GetMapping("/{projectId}/skill-gap")
    public SkillGapResponse gap(@PathVariable Long projectId, @RequestParam Long employeeId) {
        return service.skillGap(projectId, employeeId);
    }
}