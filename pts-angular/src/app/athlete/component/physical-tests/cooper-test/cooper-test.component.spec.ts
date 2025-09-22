import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CooperTestComponent } from './cooper-test.component';

describe('CooperTestComponent', () => {
  let component: CooperTestComponent;
  let fixture: ComponentFixture<CooperTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CooperTestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CooperTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
