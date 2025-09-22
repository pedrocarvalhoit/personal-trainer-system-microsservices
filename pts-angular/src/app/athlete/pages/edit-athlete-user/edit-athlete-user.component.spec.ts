import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAthleteUserComponent } from './edit-athlete-user.component';

describe('EditAthleteUserComponent', () => {
  let component: EditAthleteUserComponent;
  let fixture: ComponentFixture<EditAthleteUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAthleteUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditAthleteUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
