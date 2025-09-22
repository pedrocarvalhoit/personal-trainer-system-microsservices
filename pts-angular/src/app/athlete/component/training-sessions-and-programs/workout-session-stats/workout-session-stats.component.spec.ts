import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutSessionStatsComponent } from './workout-session-stats.component';

describe('WorkoutSessionStatsComponent', () => {
  let component: WorkoutSessionStatsComponent;
  let fixture: ComponentFixture<WorkoutSessionStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkoutSessionStatsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkoutSessionStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
