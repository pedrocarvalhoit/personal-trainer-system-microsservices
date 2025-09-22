import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatedLowRowComponent } from './seated-low-row.component';

describe('SeatedLowRowComponent', () => {
  let component: SeatedLowRowComponent;
  let fixture: ComponentFixture<SeatedLowRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeatedLowRowComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SeatedLowRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
