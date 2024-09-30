import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-view-wishlist',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule],
  templateUrl: './view-wishlist.component.html',
  styleUrl: './view-wishlist.component.scss'
})
export class ViewWishlistComponent {

  products:any[] = [];

  constructor(private customerService: CustomerService){}

  ngOnInit(){
    this.getWishlistByUserId();
  }

  getWishlistByUserId(){
    this.customerService.getWishlistByUserId().subscribe(
      res => {
        res.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64' + element.returnedImg;
          this.products.push(element);
        });
      }
    )
  }

}
