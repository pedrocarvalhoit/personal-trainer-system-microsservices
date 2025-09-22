import { TestBed } from '@angular/core/testing';

import { PerimetryAssessmentService } from './perimetry-assessment.service';

describe('PerimetryAssessmentService', () => {
  let service: PerimetryAssessmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PerimetryAssessmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
