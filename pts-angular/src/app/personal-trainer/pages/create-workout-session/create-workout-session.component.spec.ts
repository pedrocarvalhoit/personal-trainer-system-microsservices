import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateWorkoutSessionComponent } from './create-workout-session.component';

describe('CreateWorkoutSessionComponent', () => {
  let component: CreateWorkoutSessionComponent;
  let fixture: ComponentFixture<CreateWorkoutSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateWorkoutSessionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateWorkoutSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
