export interface Skill {
  id?: number;
  name: string;
  description?: string;
  level?: number;      // 1..5
  category?: string;
  active?: boolean;
}