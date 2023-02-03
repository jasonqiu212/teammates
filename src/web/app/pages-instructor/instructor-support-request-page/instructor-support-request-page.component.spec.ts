import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorSupportRequestPageComponent } from './instructor-support-request-page.component';

describe('InstructorSupportRequestPageComponent', () => {
  let component: InstructorSupportRequestPageComponent;
  let fixture: ComponentFixture<InstructorSupportRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorSupportRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorSupportRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
