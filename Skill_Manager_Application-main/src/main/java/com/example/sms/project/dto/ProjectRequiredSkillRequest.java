package com.example.sms.project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProjectRequiredSkillRequest(
        @NotNull Long projectId,
        @NotNull Long skillId,
        @Min(1) @Max(5) int requiredLevel
) {}