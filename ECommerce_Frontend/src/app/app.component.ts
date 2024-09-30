import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { DemoAngularMaterial } from './DemoAngularMaterial';
import { CommonModule } from '@angular/common';
import { provideAnimations } from '@angular/platform-browser/animations';
import { UserStorageService } from './services/storage/user-storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DemoAngularMaterial, CommonModule, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [
    provideAnimations()
  ]
})

export class AppComponent {
  title = 'Mega-ECommerceWeb';

  isCustomerLoggedIn: boolean = UserStorageService.isCustomerLoggedIn(); 
  isAdminLoggedIn: boolean = UserStorageService.isAdminLoggedIn(); 

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
      this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
    })
  }

  logout(){
    UserStorageService.signOut();
    this.router.navigateByUrl('/login');
  }


  
}
