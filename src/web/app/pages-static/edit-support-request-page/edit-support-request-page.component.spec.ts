import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSupportRequestPageComponent } from './edit-support-request-page.component';

describe('EditSupportRequestPageComponent', () => {
  let component: EditSupportRequestPageComponent;
  let fixture: ComponentFixture<EditSupportRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditSupportRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSupportRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
