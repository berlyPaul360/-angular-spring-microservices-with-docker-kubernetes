import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";

const routes : Routes =[
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({
  declarations: [],

  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
