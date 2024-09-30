import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-view-ordered-products',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule, RouterModule],
  templateUrl: './view-ordered-products.component.html',
  styleUrl: './view-ordered-products.component.scss'
})
export class ViewOrderedProductsComponent {

  orderId: any;
  orderedProductDetailsList = [];
  totalAmount: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private customerService: CustomerService
  ){
    this.orderId = this.activatedRoute.snapshot.params['orderId'];
  }

  ngOnInit(){
    this.getOrderedProductsDetailsByOrderId();
  }

  getOrderedProductsDetailsByOrderId(){
    this.customerService.getOrderedProducts(this.orderId).subscribe(
      res => {
        res.productDtoList.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64' + element.byteImg;
          this.orderedProductDetailsList.push(element);
        });
        this.totalAmount = res.orderAmount;
      }
    )
  }

}
