import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenchPressComponent } from './bench-press.component';

describe('BenchPressComponent', () => {
  let component: BenchPressComponent;
  let fixture: ComponentFixture<BenchPressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BenchPressComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BenchPressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
