import { TestBed } from '@angular/core/testing';

import { BioimpedanceRecommendationService } from './bioimpedance-recommendation.service';

describe('BioimpedanceRecommendationService', () => {
  let service: BioimpedanceRecommendationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BioimpedanceRecommendationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
