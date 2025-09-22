import { TestBed } from '@angular/core/testing';

import { AthleteReportService } from './athlete-report.service';

describe('AthleteReportService', () => {
  let service: AthleteReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AthleteReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
