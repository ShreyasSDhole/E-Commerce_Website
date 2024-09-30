import { Component } from '@angular/core';
import { DemoAngularMaterial } from '../../../DemoAngularMaterial';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../service/admin.service';
import { OrderByStatusComponent } from "./order-by-status/order-by-status.component";

@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [DemoAngularMaterial, CommonModule, OrderByStatusComponent],
  templateUrl: './analytics.component.html',
  styleUrl: './analytics.component.scss'
})
export class AnalyticsComponent {

  data:any;

  constructor(private adminService: AdminService){}

  ngOnInit(){
    this.adminService.getAnalytics().subscribe(
      res => {
        console.log(res);
        this.data = res;
      }
    )
  }
}
