import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCooperTestComponent } from './create-cooper-test.component';

describe('CreateCooperTestComponent', () => {
  let component: CreateCooperTestComponent;
  let fixture: ComponentFixture<CreateCooperTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCooperTestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateCooperTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
