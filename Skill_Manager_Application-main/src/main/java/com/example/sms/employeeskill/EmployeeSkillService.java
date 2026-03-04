package com.example.sms.employeeskill;

import com.example.sms.skill.SkillRepository;
import com.example.sms.user.User;
import com.example.sms.user.UserRepository;
import com.example.sms.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeSkillService {

    private final EmployeeSkillRepository repo;
    private final SkillRepository skillRepo;
    private final UserRepository userRepo;

    public EmployeeSkillService(EmployeeSkillRepository repo,
                                SkillRepository skillRepo,
                                UserRepository userRepo) {
        this.repo = repo;
        this.skillRepo = skillRepo;
        this.userRepo = userRepo;
    }

    public List<EmployeeSkill> listForUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Transactional
    public EmployeeSkill upsert(User user, Long skillId, int level, double years) {
        ValidationUtil.requireRange(level, 1, 5, "proficiencyLevel must be 1..5");
        ValidationUtil.requireNonNegative(years, "yearsOfExperience must be >= 0");

        var skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        EmployeeSkill existing = repo.findByUserIdAndSkillId(user.getId(), skillId).orElse(null);
        if (existing == null) {
            existing = new EmployeeSkill();
            existing.setUser(user);
            existing.setSkill(skill);
            existing.setProficiencyLevel(level);
            existing.setYearsOfExperience(years);
        } else {
            existing.setProficiencyLevel(level);
            existing.setYearsOfExperience(years);
        }
        return repo.save(existing);
    }
}