import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BioimpedanceAssessmentComponent } from './bioimpedance-assessment.component';

describe('BioimpedanceAssessmentComponent', () => {
  let component: BioimpedanceAssessmentComponent;
  let fixture: ComponentFixture<BioimpedanceAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BioimpedanceAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BioimpedanceAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
