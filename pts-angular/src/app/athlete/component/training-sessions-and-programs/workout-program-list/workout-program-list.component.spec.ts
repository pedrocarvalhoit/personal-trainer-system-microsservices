import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutProgramListComponent } from './workout-program-list.component';

describe('WorkoutProgramListComponent', () => {
  let component: WorkoutProgramListComponent;
  let fixture: ComponentFixture<WorkoutProgramListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkoutProgramListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkoutProgramListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
