import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CooperTestResultComponent } from './cooper-test-result.component';

describe('CooperTestResultComponent', () => {
  let component: CooperTestResultComponent;
  let fixture: ComponentFixture<CooperTestResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CooperTestResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CooperTestResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
