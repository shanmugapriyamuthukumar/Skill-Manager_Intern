package com.example.sms.employeeskill;

import com.example.sms.skills.Skill;
import com.example.sms.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_skills",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "skill_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeSkill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional=false) @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(nullable=false)
    private int proficiencyLevel; // 1-5

    @Column(nullable=false)
    private double yearsOfExperience; // >= 0

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(int proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	public double getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
    
    
}