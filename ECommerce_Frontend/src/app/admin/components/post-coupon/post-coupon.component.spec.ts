import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCouponComponent } from './post-coupon.component';

describe('PostCouponComponent', () => {
  let component: PostCouponComponent;
  let fixture: ComponentFixture<PostCouponComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCouponComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
