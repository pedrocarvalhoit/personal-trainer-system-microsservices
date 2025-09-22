import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateInicialAssessmentComponent } from './create-inicial-assessment.component';

describe('CreateInicialAssessmentComponent', () => {
  let component: CreateInicialAssessmentComponent;
  let fixture: ComponentFixture<CreateInicialAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateInicialAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateInicialAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
