import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackSquatComponent } from './back-squat.component';

describe('BackSquatComponent', () => {
  let component: BackSquatComponent;
  let fixture: ComponentFixture<BackSquatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackSquatComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BackSquatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
