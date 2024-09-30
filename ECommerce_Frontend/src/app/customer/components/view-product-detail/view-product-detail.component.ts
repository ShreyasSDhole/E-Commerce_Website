import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserStorageService } from '../../../services/storage/user-storage.service';

@Component({
  selector: 'app-view-product-detail',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule],
  templateUrl: './view-product-detail.component.html',
  styleUrl: './view-product-detail.component.scss'
})
export class ViewProductDetailComponent {

  productId: number;
  product: any;
  FAQs: any[] = [];
  reviews: any[] = [];

  constructor(
    private snackBar: MatSnackBar,
    private customerService: CustomerService,
    private activatedRoute : ActivatedRoute
  ){
    this.productId = this.activatedRoute.snapshot.params["productId"];
  }

  ngOnInit(){
    this.getProductDetailById();
  }

  getProductDetailById(){
    this.customerService.getProductDetailById(this.productId).subscribe(
      res => {
        this.product = res.productDto;
        this.product.processedImg = 'data:image/png;base64' + res.productDto.byteImg;

        this.FAQs = res.faqDtoList;

        res.reviewDtoList.forEach(element => {
          element.processedImg = 'data:image/png;base64' + element.returnedImg;
          this.reviews.push(element);
        });
      }
    )
  }

  addToWishlist(){
    const wishlistDto = {
      productId: this.productId,
      userId: UserStorageService.getUserId()
    }

    this.customerService.addProductToWishlist(wishlistDto).subscribe(
      res => {
        if(res.id != null){
          this.snackBar.open('Product added to Wishlist successfully', 'Close', {duration: 5000});
        }
        else{
          this.snackBar.open('Already in Wishlist', 'Error', {duration: 5000});
        }
      }
    )
  }

}
