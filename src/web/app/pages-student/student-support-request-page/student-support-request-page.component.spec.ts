import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentSupportRequestPageComponent } from './student-support-request-page.component';

describe('StudentSupportRequestPageComponent', () => {
  let component: StudentSupportRequestPageComponent;
  let fixture: ComponentFixture<StudentSupportRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentSupportRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentSupportRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
