package com.example.sms.project.dto;

import java.util.List;

public record SkillGapResponse(
        Long projectId,
        Long employeeId,
        List<String> missingSkills,
        List<Gap> gaps
) {
    public record Gap(String skill, int employeeLevel, int requiredLevel) {}
}