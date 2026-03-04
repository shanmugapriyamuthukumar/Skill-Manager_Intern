import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSkill } from './employee-skill';

describe('EmployeeSkill', () => {
  let component: EmployeeSkill;
  let fixture: ComponentFixture<EmployeeSkill>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeSkill],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeSkill);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
