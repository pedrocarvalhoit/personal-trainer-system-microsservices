import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AthleteReportComponent } from './athlete-report.component';

describe('AthleteReportComponent', () => {
  let component: AthleteReportComponent;
  let fixture: ComponentFixture<AthleteReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AthleteReportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AthleteReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
