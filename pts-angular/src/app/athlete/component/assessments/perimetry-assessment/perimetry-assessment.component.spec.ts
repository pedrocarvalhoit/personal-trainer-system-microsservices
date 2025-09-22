import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerimetryAssessmentComponent } from './perimetry-assessment.component';

describe('PerimetryAssessmentComponent', () => {
  let component: PerimetryAssessmentComponent;
  let fixture: ComponentFixture<PerimetryAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerimetryAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PerimetryAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
