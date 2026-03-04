import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-employee-skill',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  template: `
    <h3>Employee Skill</h3>

    <input type="number" [(ngModel)]="skillId" placeholder="Skill ID">
    <input type="number" [(ngModel)]="proficiency" placeholder="Level (1-5)">
    <input type="number" [(ngModel)]="experience" placeholder="Years">

    <button (click)="save()">Save</button>
  `
})
export class EmployeeSkillComponent {

  skillId!: number;
  proficiency!: number;
  experience!: number;

  private baseUrl = 'http://localhost:9090/api/employee/skills';

  constructor(private http: HttpClient) {}

  save() {

    const payload = {
      skillId: this.skillId,
      proficiencyLevel: this.proficiency,
      yearsOfExperience: this.experience
    };

    this.http.post(this.baseUrl, payload)
      .subscribe({
        next: () => {
          alert('Saved Successfully');
          this.skillId = 0;
          this.proficiency = 0;
          this.experience = 0;
        },
        error: (err) => {
          console.error(err);
          alert('Error saving skill');
        }
      });
  }
}