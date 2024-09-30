import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { timeStamp } from 'console';

@Component({
  selector: 'app-post-product-faq',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule],
  templateUrl: './post-product-faq.component.html',
  styleUrl: './post-product-faq.component.scss'
})
export class PostProductFaqComponent {

  productId: number;
  FAQForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ){
    this.productId = this.activatedRoute.snapshot.params["productId"];
  }

  ngOnInit(){
    this.FAQForm = this.fb.group({
        question: [null, [Validators.required]],
        answer: [null, [Validators.required]],
    })
  }

  postFAQ(){
    this.adminService.postFAQ(this.productId, this.FAQForm.value).subscribe(
      res => {
        if(res.id != null){
          this.snackBar.open("FAQ posted successfully", 'Close', {duration: 5000});
          this.router.navigateByUrl('/admin/dashboard');
        }
        else{
          this.snackBar.open("Something went Wrong", 'Close', {duration: 5000, panelClass: 'error-snackBar'});
        }
      }
    )
  }
}
