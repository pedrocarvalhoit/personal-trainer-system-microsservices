import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InicialAssessmentComponent } from './inicial-assessment.component';

describe('InicialAssessmentComponent', () => {
  let component: InicialAssessmentComponent;
  let fixture: ComponentFixture<InicialAssessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InicialAssessmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InicialAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
