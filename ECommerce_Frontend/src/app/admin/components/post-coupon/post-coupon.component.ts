import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-coupon',
  standalone: true,
  imports: [DemoAngularMaterial],
  templateUrl: './post-coupon.component.html',
  styleUrl: './post-coupon.component.scss'
})
export class PostCouponComponent {
  couponForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ){}

  ngOnInit(){
    this.couponForm = this.fb.group({
      name: [null,[Validators.required]],
      code: [null, [Validators.required]],
      discount: [null, [Validators.required]],
      expirationDate: [null, [Validators.required]]
    })
  }

  addCoupon(){
    if(this.couponForm.valid){
      this.adminService.addCoupon(this.couponForm.value).subscribe(
        res => {
          if(res.id != null){
            this.snackBar.open('Coupon posted successfully', 'Close', {duration: 5000});
            this.router.navigateByUrl('/admin/dashboard');
          }
          else{
            this.snackBar.open(res.message, 'Close', {duration: 5000, panelClass: 'error-snackBar'});
          }
        }
      )
    }
    else{
      this.couponForm.markAllAsTouched();
    }
  }
}
