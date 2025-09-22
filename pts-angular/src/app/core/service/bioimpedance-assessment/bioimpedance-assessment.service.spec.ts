import { TestBed } from '@angular/core/testing';

import { BioimpedanceAssessmentService } from './bioimpedance-assessment.service';

describe('BioimpedanceAssessmentService', () => {
  let service: BioimpedanceAssessmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BioimpedanceAssessmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
