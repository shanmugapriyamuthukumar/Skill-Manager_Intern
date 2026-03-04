import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class SkillService {

  private baseUrl = 'http://localhost:9092/api/skills';

  constructor(private http: HttpClient) {}

  getSkills() {
    return this.http.get<any[]>(this.baseUrl);
  }

  addSkill(skill: any) {
    return this.http.post(this.baseUrl, skill);
  }

  updateSkill(id: number, skill: any) {
    return this.http.put(`${this.baseUrl}/${id}`, skill);
  }

  deleteSkill(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}