package com.example.sms.employeeskill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
    List<EmployeeSkill> findByUserId(Long userId);
    Optional<EmployeeSkill> findByUserIdAndSkillId(Long userId, Long skillId);
}
