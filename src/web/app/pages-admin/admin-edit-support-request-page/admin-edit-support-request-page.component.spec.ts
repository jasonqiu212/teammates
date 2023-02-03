import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEditSupportRequestPageComponent } from './admin-edit-support-request-page.component';

describe('AdminEditSupportRequestPageComponent', () => {
  let component: AdminEditSupportRequestPageComponent;
  let fixture: ComponentFixture<AdminEditSupportRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEditSupportRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEditSupportRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
