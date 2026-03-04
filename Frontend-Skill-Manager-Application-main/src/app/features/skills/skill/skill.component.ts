import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-skill',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './skill.html'
})
export class SkillComponent implements OnInit {

  skills: any[] = [];
  skillName!: string;

  private baseUrl = 'http://localhost:9090/api/skills';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadSkills();
  }

  loadSkills() {
    this.http.get<any[]>(this.baseUrl)
      .subscribe(data => this.skills = data);
  }

  addSkill() {
    if (!this.skillName) return;

    this.http.post(this.baseUrl, { name: this.skillName })
      .subscribe(() => {
        this.skillName = '';
        this.loadSkills();
      });
  }

  deleteSkill(id: number) {
    this.http.delete(`${this.baseUrl}/${id}`)
      .subscribe(() => this.loadSkills());
  }
}