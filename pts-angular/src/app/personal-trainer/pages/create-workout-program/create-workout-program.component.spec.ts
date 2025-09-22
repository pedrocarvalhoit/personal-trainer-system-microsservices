import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateWorkoutProgramComponent } from './create-workout-program.component';

describe('CreateWorkoutProgramComponent', () => {
  let component: CreateWorkoutProgramComponent;
  let fixture: ComponentFixture<CreateWorkoutProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateWorkoutProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateWorkoutProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
