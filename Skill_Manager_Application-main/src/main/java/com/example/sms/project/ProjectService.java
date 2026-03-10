package com.example.sms.project;

import com.example.sms.employeeskill.EmployeeSkillRepository;

import com.example.sms.project.dto.ProjectCreateRequest;
import com.example.sms.project.dto.ProjectRequiredSkillRequest;
import com.example.sms.project.dto.SkillGapResponse;
import com.example.sms.skills.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final ProjectRequiredSkillRepository prsRepo;
    private final SkillRepository skillRepo;
    private final EmployeeSkillRepository empSkillRepo;

    public ProjectService(ProjectRepository projectRepo,
                          ProjectRequiredSkillRepository prsRepo,
                          SkillRepository skillRepo,
                          EmployeeSkillRepository empSkillRepo) {
        this.projectRepo = projectRepo;
        this.prsRepo = prsRepo;
        this.skillRepo = skillRepo;
        this.empSkillRepo = empSkillRepo;
    }

    public List<Project> list() {
        return projectRepo.findAll();
    }

    @Transactional
    public Project create(ProjectCreateRequest req) {
        // Setter-based, no Lombok builder
        Project p = new Project();
        p.setProjectName(req.projectName());
        return projectRepo.save(p);
    }

    @Transactional
    public ProjectRequiredSkill addRequiredSkill(ProjectRequiredSkillRequest req) {
        Project p = projectRepo.findById(req.projectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        var s = skillRepo.findById(req.skillId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        // Setter-based, no Lombok builder
        ProjectRequiredSkill prs = new ProjectRequiredSkill();
        prs.setProject(p);
        prs.setSkill(s);
        prs.setRequiredLevel(req.requiredLevel());

        return prsRepo.save(prs);
    }

    public List<ProjectRequiredSkill> requiredSkills(Long projectId) {
        return prsRepo.findByProjectId(projectId);
    }

    public SkillGapResponse skillGap(Long projectId, Long employeeId) {
        var reqSkills = prsRepo.findByProjectId(projectId);
        var employeeSkills = empSkillRepo.findByUserId(employeeId);

        Map<Long, Integer> empLevels = new HashMap<>();
        employeeSkills.forEach(es -> empLevels.put(es.getSkill().getId(), es.getProficiencyLevel()));

        List<String> missing = new ArrayList<>();
        List<SkillGapResponse.Gap> gaps = new ArrayList<>();

        reqSkills.forEach(rs -> {
            Integer lvl = empLevels.get(rs.getSkill().getId());
            if (lvl == null) {
                missing.add(rs.getSkill().getName());
            } else if (lvl < rs.getRequiredLevel()) {
                gaps.add(new SkillGapResponse.Gap(
                        rs.getSkill().getName(), lvl, rs.getRequiredLevel()
                ));
            }
        });

        return new SkillGapResponse(projectId, employeeId, missing, gaps);
    }
}