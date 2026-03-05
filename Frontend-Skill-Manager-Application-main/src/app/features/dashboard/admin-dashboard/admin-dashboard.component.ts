import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';   // needed for [(ngModel)]

export interface Skill {
  id?: number;
  name: string;
  description?: string;
  level?: number;
  category?: string;
  active?: boolean;
}

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],          // <-- import here
  templateUrl: './admin-dashboard.html',
  styleUrls: ['./admin-dashboard.css']
})
export class AdminDashboardComponent {
  // In-memory demo data (no service)
  skills: Skill[] = [
    { id: 1, name: 'Java', description: 'Core + Spring', level: 5, category: 'Backend', active: true },
    { id: 2, name: 'Angular', description: 'SPA Framework', level: 4, category: 'Frontend', active: true }
  ];

  form: Skill = { name: '', description: '', level: 1, category: '', active: true };
  isEditing = false;
  editingId?: number;
  error?: string;
  success?: string;

  submit(): void {
    this.error = undefined; this.success = undefined;

    const name = (this.form.name || '').trim();
    const level = Number(this.form.level || 1);
    if (!name) { this.error = 'Name is required'; return; }
    if (level < 1 || level > 5) { this.error = 'Level must be 1..5'; return; }

    const exists = this.skills.some(s =>
      s.name.toLowerCase() === name.toLowerCase() &&
      (!this.isEditing || s.id !== this.editingId)
    );
    if (exists) { this.error = `Skill "${name}" already exists`; return; }

    if (this.isEditing && this.editingId) {
      const i = this.skills.findIndex(s => s.id === this.editingId);
      if (i >= 0) {
        this.skills[i] = { ...this.form, id: this.editingId };
        this.success = 'Skill updated';
      }
      this.cancelEdit();
    } else {
      const nextId = this.skills.length ? Math.max(...this.skills.map(s => s.id || 0)) + 1 : 1;
      this.skills = [{ ...this.form, id: nextId }, ...this.skills];
      this.success = 'Skill created';
      this.resetForm();
    }
  }

  edit(s: Skill): void {
    this.isEditing = true;
    this.editingId = s.id;
    this.form = { ...s };
  }

  cancelEdit(): void {
    this.isEditing = false;
    this.editingId = undefined;
    this.resetForm();
  }

  resetForm(): void {
    this.form = { name: '', description: '', level: 1, category: '', active: true };
  }

  remove(id?: number): void {
    if (!id) return;
    if (!confirm('Delete this skill?')) return;
    this.skills = this.skills.filter(s => s.id !== id);
    this.success = 'Skill deleted';
  }
}