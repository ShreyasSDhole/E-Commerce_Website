import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { CommonModule, DATE_PIPE_DEFAULT_OPTIONS } from '@angular/common';

@Component({
  selector: 'app-coupons',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule],
  templateUrl: './coupons.component.html',
  styleUrl: './coupons.component.scss',
  providers: [
    {provide: DATE_PIPE_DEFAULT_OPTIONS, useValue: {dateFormat: 'shortDate'}}
  ]
})
export class CouponsComponent {
  coupons: any;


  constructor(private adminService: AdminService){}

  ngOnInit(){
    this.getCoupons();
  }

  getCoupons(){
    this.adminService.getCoupons().subscribe(
      res => {
        this.coupons = res;
      }
    )
  }
}
