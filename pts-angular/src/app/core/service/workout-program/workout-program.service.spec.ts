import { TestBed } from '@angular/core/testing';

import { WorkoutProgramService } from './workout-program.service';

describe('WorkoutProgramService', () => {
  let service: WorkoutProgramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkoutProgramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
