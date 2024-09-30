import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-place-order',
  standalone: true,
  imports: [DemoAngularMaterial],
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.scss'
})
export class PlaceOrderComponent {
  orderForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
    private router: Router,
    private dialog: MatDialog
  ){}

  ngOnInit(){
    this.orderForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDescription: [null]
    })
  }

  placeOrder(){
    this.customerService.placeOrder(this.orderForm.value).subscribe(
      res => {
        if(res.id != null){
          this.snackBar.open("Order placed successfully", 'Close', {duration: 5000});
          this.router.navigateByUrl('/customer/my-orders');
          this.closeForm();
        }
        else{
          this.snackBar.open("Something went wrong", 'Close', {duration: 5000});
        }
      }
    )
  }

  closeForm(){
    this.dialog.closeAll();
  }
}
