import { NgModule } from "@angular/core";
import { RouterLink } from "@angular/router";
import { RouterModule } from "@angular/router";

import { BrowserAnimationsModule, provideAnimations } from "@angular/platform-browser/animations";
import { DemoAngularMaterial } from "./DemoAngularMaterial";
import { MatCardModule } from "@angular/material/card";
import { AppComponent } from "./app.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { provideHttpClient, withFetch } from "@angular/common/http";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./login/login.component";
import { SignupComponent } from "./signup/signup.component";

@NgModule({
    imports: [
        AppComponent,
        LoginComponent,
        SignupComponent,
        BrowserAnimationsModule,
        CommonModule,
        RouterLink,
        DemoAngularMaterial,
        RouterModule,
        MatCardModule,
        FormsModule,
        ReactiveFormsModule,
    ],

    providers: [
        provideHttpClient(withFetch()),
        provideAnimations(),
    ],
})
export class AppModule {}