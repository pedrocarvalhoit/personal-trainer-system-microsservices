import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CooperTestHistoricComponent } from './cooper-test-historic.component';

describe('CooperTestHistoricComponent', () => {
  let component: CooperTestHistoricComponent;
  let fixture: ComponentFixture<CooperTestHistoricComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CooperTestHistoricComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CooperTestHistoricComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
