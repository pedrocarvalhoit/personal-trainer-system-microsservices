import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeadliftComponent } from './deadlift.component';

describe('DeadliftComponent', () => {
  let component: DeadliftComponent;
  let fixture: ComponentFixture<DeadliftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeadliftComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeadliftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
