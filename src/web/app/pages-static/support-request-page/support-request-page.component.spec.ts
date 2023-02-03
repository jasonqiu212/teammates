import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportRequestPageComponent } from './support-request-page.component';

describe('SupportRequestPageComponent', () => {
  let component: SupportRequestPageComponent;
  let fixture: ComponentFixture<SupportRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupportRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupportRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
