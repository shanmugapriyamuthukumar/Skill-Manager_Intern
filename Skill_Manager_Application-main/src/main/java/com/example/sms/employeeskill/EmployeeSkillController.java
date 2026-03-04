package com.example.sms.employeeskill;

import com.example.sms.user.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/skills")
public class EmployeeSkillController {

    private final EmployeeSkillService service;
    private final UserService userService;

    public EmployeeSkillController(EmployeeSkillService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    // Get current user's skills
    @GetMapping
    public List<EmployeeSkill> mySkills(Authentication auth) {
        var user = userService.getCurrentUser(auth);
        return service.listForUser(user.getId());
    }

    // Add/Update current user's skill
    @PostMapping
    public EmployeeSkill upsert(Authentication auth, @RequestBody EmployeeSkillRequest req) {
        var user = userService.getCurrentUser(auth);
        return service.upsert(user, req.skillId(), req.proficiencyLevel(), req.yearsOfExperience());
    }

    // Local DTO as requested (since not in your file tree)
    public static record EmployeeSkillRequest(
            @NotNull Long skillId,
            @Min(1) @Max(5) int proficiencyLevel,
            @PositiveOrZero double yearsOfExperience
    ) {}
}