package com.example.sms.project;

import com.example.sms.skill.Skill;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_required_skills",
       uniqueConstraints = @UniqueConstraint(columnNames = {"project_id","skill_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProjectRequiredSkill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(optional=false) @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(nullable=false)
    private int requiredLevel; // 1-5

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}
    
    
}