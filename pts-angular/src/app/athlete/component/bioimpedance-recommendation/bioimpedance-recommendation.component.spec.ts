import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BioimpedanceRecommendationComponent } from './bioimpedance-recommendation.component';

describe('BioimpedanceRecommendationComponent', () => {
  let component: BioimpedanceRecommendationComponent;
  let fixture: ComponentFixture<BioimpedanceRecommendationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BioimpedanceRecommendationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BioimpedanceRecommendationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
