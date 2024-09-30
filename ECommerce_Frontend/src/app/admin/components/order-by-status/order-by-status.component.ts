import { Component, Input } from '@angular/core';
import { DemoAngularMaterial } from '../../../../DemoAngularMaterial';

@Component({
  selector: 'app-order-by-status',
  standalone: true,
  imports: [DemoAngularMaterial],
  templateUrl: './order-by-status.component.html',
  styleUrl: './order-by-status.component.scss'
})
export class OrderByStatusComponent {

  @Input() data:any;
}
