import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AthletesListComponent } from './athletes-list.component';

describe('AthletesListComponent', () => {
  let component: AthletesListComponent;
  let fixture: ComponentFixture<AthletesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AthletesListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AthletesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
