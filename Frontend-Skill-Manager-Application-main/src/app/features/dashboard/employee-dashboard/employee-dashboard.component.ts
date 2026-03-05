import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-dashboard.html',
  styleUrls: ['./employee-dashboard.css']
})
export class EmployeeDashboardComponent {
  userName: string = "User Name";
  employeeId: string = "266XXXX";
  projectName: string = "Mobile Application";
  projectId: string = "235457567";

  skills = [
    { skillId: 101, skillName: "Java", proficiency: 4 },
    { skillId: 102, skillName: "Angular", proficiency: 3 },
    { skillId: 103, skillName: "SQL", proficiency: 5 }
  ];
}
