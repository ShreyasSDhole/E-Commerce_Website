import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-category',
  standalone: true,
  imports: [DemoAngularMaterial],
  templateUrl: './post-category.component.html',
  styleUrl: './post-category.component.scss'
})
export class PostCategoryComponent {
  categoryForm!:FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ){}

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]]
    })
  }

  addCategory(): void{
    if(this.categoryForm.valid){
      this.adminService.addCategory(this.categoryForm.value).subscribe(
        (res) =>{
          if(res.id != null){
            this.snackBar.open('Category posted successfully', 'Close', {duration: 5000});
            this.router.navigateByUrl('/admin/dashboard');
          }
          else{
            this.snackBar.open(res.message, 'Close', {duration: 5000, panelClass: 'error-snackBar'});
          }
        }
      )
    }
    else{
      this.categoryForm.markAllAsTouched();
    }
  }
}
