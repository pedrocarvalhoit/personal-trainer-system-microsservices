import { TestBed } from '@angular/core/testing';

import { InicialAssessmentService } from './inicial-assessment.service';

describe('InicialAssessmentService', () => {
  let service: InicialAssessmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InicialAssessmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
