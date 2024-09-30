import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule, RouterModule],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.scss'
})
export class MyOrdersComponent {
  myOrders: any;

  constructor( private customerService: CustomerService){}

  ngOnInit(){
    this.getMyOrders();
  }

  getMyOrders(){
    this.customerService.getOrdersByUserId().subscribe(
      res => {
        this.myOrders = res;

      }
    )
  }
}
