import { TestBed } from '@angular/core/testing';

import { CooperTestService } from './cooper-test.service';

describe('CooperTestService', () => {
  let service: CooperTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CooperTestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
