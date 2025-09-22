import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePerimetryAssessmentComponent } from './create-perimetry-assessment.component';

describe('CreatePerimetryAssessmentComponent', () => {
  let component: CreatePerimetryAssessmentComponent;
  let fixture: ComponentFixture<CreatePerimetryAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePerimetryAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatePerimetryAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
