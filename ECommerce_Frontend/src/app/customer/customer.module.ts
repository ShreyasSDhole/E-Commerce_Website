import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideAnimations } from '@angular/platform-browser/animations';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomerComponent } from './customer.component';
import { CartComponent } from './components/cart/cart.component';



@NgModule({
  declarations: [],
  imports: [
    CommonModule, 
    CustomerComponent,
    CartComponent,
    DashboardComponent,
    FormsModule,
    ReactiveFormsModule,
    DemoAngularMaterial
  ],  
  providers: [
    provideAnimations()
  ]
})
export class CustomerModule { }
