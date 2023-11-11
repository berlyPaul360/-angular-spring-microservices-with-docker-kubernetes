import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import {RouterLink, RouterOutlet} from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSidenavModule} from "@angular/material/sidenav";
import { Menu } from './menu/menu';
import {NgOptimizedImage} from "@angular/common";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import { HomeComponent } from './home/home.component';
import {MatCardModule} from "@angular/material/card";
import {CdkDropList} from "@angular/cdk/drag-drop";

@NgModule({
  declarations: [
    AppComponent,
    Menu,
    LoginComponent,
    RegistrationComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterOutlet,
    BrowserAnimationsModule,
    MatSidenavModule,
    RouterLink,
    NgOptimizedImage,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    CdkDropList
  ],
  providers: [

  ],
  bootstrap: [AppComponent],
  schemas:[
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
