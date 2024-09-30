import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { provideAnimations } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AdminComponent,
    DashboardComponent,
    FormsModule,
    ReactiveFormsModule,
    DemoAngularMaterial
  ],
  providers: [
    provideAnimations()
  ]
})
export class AdminModule { }
