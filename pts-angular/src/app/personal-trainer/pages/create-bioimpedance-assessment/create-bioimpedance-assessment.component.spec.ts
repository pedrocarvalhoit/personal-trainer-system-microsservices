import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBioimpedanceAssessmentComponent } from './create-bioimpedance-assessment.component';

describe('CreateBioimpedanceAssessmentComponent', () => {
  let component: CreateBioimpedanceAssessmentComponent;
  let fixture: ComponentFixture<CreateBioimpedanceAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateBioimpedanceAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateBioimpedanceAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
