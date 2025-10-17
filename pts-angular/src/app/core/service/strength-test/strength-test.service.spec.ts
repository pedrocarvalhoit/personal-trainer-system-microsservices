import { TestBed } from '@angular/core/testing';

import { StrengthTestService } from './strength-test.service';

describe('StrengthTestService', () => {
  let service: StrengthTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StrengthTestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
