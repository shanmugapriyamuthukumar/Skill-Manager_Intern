package com.example.sms.project;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRequiredSkillRepository extends JpaRepository<ProjectRequiredSkill, Long> {
    List<ProjectRequiredSkill> findByProjectId(Long projectId); // exact case and signature
}