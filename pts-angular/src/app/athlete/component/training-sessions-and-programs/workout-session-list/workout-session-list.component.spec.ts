import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutSessionListComponent } from './workout-session-list.component';

describe('WorkoutSessionListComponent', () => {
  let component: WorkoutSessionListComponent;
  let fixture: ComponentFixture<WorkoutSessionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkoutSessionListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkoutSessionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
