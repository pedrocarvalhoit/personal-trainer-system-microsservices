import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StrengthTestResultComponent } from './strength-test-result.component';

describe('StrengthTestResultComponent', () => {
  let component: StrengthTestResultComponent;
  let fixture: ComponentFixture<StrengthTestResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StrengthTestResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StrengthTestResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
