import {Component, OnInit} from '@angular/core';
import employee, {Employee} from "./employee";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {EmployeeService} from "./employee.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend_balloon_app_service';

  public employees : Employee | undefined;

  constructor(private employeeService:EmployeeService){}

  public getEmployees():void{
    this.employeeService.getEmployees().subscribe(

      (res:any)=>{
        console.log(res);
        this.employees=res.employeeDto;
      },
    (error:HttpErrorResponse) => {
        alert(error.message)
        console.log(error);
    }
    );
  }

  ngOnInit(): void {
    this.getEmployees();
  }


}
