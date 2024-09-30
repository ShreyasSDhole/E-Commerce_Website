import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';
import { CommonModule } from '@angular/common';
import { MatMenuPanel } from '@angular/material/menu';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss'
})
export class OrdersComponent {
  orders: any;
  menu: MatMenuPanel<any>;

  constructor(
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ){}

  ngOnInit(){
    this.getPlacedOrders();
  }

  getPlacedOrders(){
    this.adminService.getPlacedOrders().subscribe(
      res => {
        this.orders = res;
      }
    )
  }

  changeOrderStatus(orderId:number, status:string){
    this.adminService.changeOrderStatus(orderId, status).subscribe(
      res => {
        if(res.id != null){
          this.snackBar.open("Order status changed successfully", 'Close', {duration: 5000});
          this.getPlacedOrders();
        }
        else{
          this.snackBar.open("Something went wrong", 'Close', {duration: 5000});
        }
      }
    )
  }
}
